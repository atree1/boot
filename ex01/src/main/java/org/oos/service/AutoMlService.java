package org.oos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.automl.v1beta1.AnnotationPayload;
import com.google.cloud.automl.v1beta1.AutoMlClient;
import com.google.cloud.automl.v1beta1.ClassificationProto.ClassificationEvaluationMetrics;
import com.google.cloud.automl.v1beta1.ClassificationProto.ClassificationEvaluationMetrics.ConfidenceMetricsEntry;
import com.google.cloud.automl.v1beta1.ClassificationProto.ClassificationType;
import com.google.cloud.automl.v1beta1.Dataset;
import com.google.cloud.automl.v1beta1.DatasetName;
import com.google.cloud.automl.v1beta1.ExamplePayload;
import com.google.cloud.automl.v1beta1.GcsSource;
import com.google.cloud.automl.v1beta1.Image;
import com.google.cloud.automl.v1beta1.ImageClassificationDatasetMetadata;
import com.google.cloud.automl.v1beta1.ImageClassificationModelMetadata;
import com.google.cloud.automl.v1beta1.InputConfig;
import com.google.cloud.automl.v1beta1.ListModelEvaluationsRequest;
import com.google.cloud.automl.v1beta1.LocationName;
import com.google.cloud.automl.v1beta1.Model;
import com.google.cloud.automl.v1beta1.ModelEvaluation;
import com.google.cloud.automl.v1beta1.ModelEvaluationName;
import com.google.cloud.automl.v1beta1.ModelName;
import com.google.cloud.automl.v1beta1.OperationMetadata;
import com.google.cloud.automl.v1beta1.PredictResponse;
import com.google.cloud.automl.v1beta1.PredictionServiceClient;
import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;

import lombok.extern.java.Log;

@Service
@Log
public class AutoMlService {
	public void createDataset(String projectId, String computeRegion, String datasetName, Boolean multiLabel)
			throws IOException {
		// Instantiates a client
		AutoMlClient client = AutoMlClient.create();

		// A resource that represents Google Cloud Platform location.
		LocationName projectLocation = LocationName.of(projectId, computeRegion);

		// Classification type assigned based on multiLabel value.
		ClassificationType classificationType = multiLabel ? ClassificationType.MULTILABEL
				: ClassificationType.MULTICLASS;

		// Specify the image classification type for the dataset.
		ImageClassificationDatasetMetadata imageClassificationDatasetMetadata = ImageClassificationDatasetMetadata
				.newBuilder().setClassificationType(classificationType).build();

		// Set dataset with dataset name and set the dataset metadata.
		Dataset myDataset = Dataset.newBuilder().setDisplayName(datasetName)
				.setImageClassificationDatasetMetadata(imageClassificationDatasetMetadata).build();

		// Create dataset with the dataset metadata in the region.
		Dataset dataset = client.createDataset(projectLocation, myDataset);

		// Display the dataset information
		log.info(String.format("Dataset name: %s", dataset.getName()));
		log.info(
				String.format("Dataset id: %s", dataset.getName().split("/")[dataset.getName().split("/").length - 1]));
		log.info(String.format("Dataset display name: %s", dataset.getDisplayName()));
		log.info("Image classification dataset specification:");
		System.out.print(String.format("\t%s", dataset.getImageClassificationDatasetMetadata()));
		log.info(String.format("Dataset example count: %d", dataset.getExampleCount()));
		log.info("Dataset create time:");
		log.info(String.format("\tseconds: %s", dataset.getCreateTime().getSeconds()));
		log.info(String.format("\tnanos: %s", dataset.getCreateTime().getNanos()));
	}

	public void importData(String projectId, String computeRegion, String datasetId, String path)
			throws Exception {
		// Instantiates a client
		AutoMlClient client = AutoMlClient.create();

		// Get the complete path of the dataset.
		DatasetName datasetFullId = DatasetName.of(projectId, computeRegion, datasetId);

		GcsSource.Builder gcsSource = GcsSource.newBuilder();

		// Get multiple training data files to be imported
		String[] inputUris = path.split(",");
		for (String inputUri : inputUris) {
			gcsSource.addInputUris(inputUri);
		}

		// Import data from the input URI
		InputConfig inputConfig = InputConfig.newBuilder().setGcsSource(gcsSource).build();
		log.info("Processing import...");
		Empty response = client.importDataAsync(datasetFullId.toString(), inputConfig).get();
		log.info(String.format("Dataset imported. %s", response));
	}

	public void createModel(String projectId, String computeRegion, String dataSetId, String modelName,
			String trainBudget) throws Exception {
		// Instantiates a client
		AutoMlClient client = AutoMlClient.create();

		// A resource that represents Google Cloud Platform location.
		LocationName projectLocation = LocationName.of(projectId, computeRegion);

		// Set model metadata.
		ImageClassificationModelMetadata imageClassificationModelMetadata = Long.valueOf(trainBudget) == 0
				? ImageClassificationModelMetadata.newBuilder().build()
				: ImageClassificationModelMetadata.newBuilder().setTrainBudget(Long.valueOf(trainBudget)).build();

		// Set model name and model metadata for the image dataset.
		Model myModel = Model.newBuilder().setDisplayName(modelName).setDatasetId(dataSetId)
				.setImageClassificationModelMetadata(imageClassificationModelMetadata).build();

		// Create a model with the model metadata in the region.
		OperationFuture<Model, OperationMetadata> response = client.createModelAsync(projectLocation, myModel);

		log.info(String.format("Training operation name: %s", response.getInitialFuture().get().getName()));
		log.info("Training started...");
	}

	public void displayEvaluation(String projectId, String computeRegion, String modelId, String filter)
			throws IOException {
		AutoMlClient client = AutoMlClient.create();

		// Get the full path of the model.
		ModelName modelFullId = ModelName.of(projectId, computeRegion, modelId);

		// List all the model evaluations in the model by applying filter.
		ListModelEvaluationsRequest modelEvaluationsrequest = ListModelEvaluationsRequest.newBuilder()
				.setParent(modelFullId.toString()).setFilter(filter).build();

		// Iterate through the results.
		String modelEvaluationId = "";
		for (ModelEvaluation element : client.listModelEvaluations(modelEvaluationsrequest).iterateAll()) {
			if (element.getAnnotationSpecId() != null) {
				modelEvaluationId = element.getName().split("/")[element.getName().split("/").length - 1];
			}
		}

		// Resource name for the model evaluation.
		ModelEvaluationName modelEvaluationFullId = ModelEvaluationName.of(projectId, computeRegion, modelId,
				modelEvaluationId);

		// Get a model evaluation.
		ModelEvaluation modelEvaluation = client.getModelEvaluation(modelEvaluationFullId);

		ClassificationEvaluationMetrics classMetrics = modelEvaluation.getClassificationEvaluationMetrics();
		List<ConfidenceMetricsEntry> confidenceMetricsEntries = classMetrics.getConfidenceMetricsEntryList();

		// Showing model score based on threshold of 0.5
		for (ConfidenceMetricsEntry confidenceMetricsEntry : confidenceMetricsEntries) {
			if (confidenceMetricsEntry.getConfidenceThreshold() == 0.5) {
				log.info("Precision and recall are based on a score threshold of 0.5");
				log.info(
						String.format("Model Precision: %.2f ", confidenceMetricsEntry.getPrecision() * 100) + '%');
				System.out
						.println(String.format("Model Recall: %.2f ", confidenceMetricsEntry.getRecall() * 100) + '%');
				log.info(
						String.format("Model F1 score: %.2f ", confidenceMetricsEntry.getF1Score() * 100) + '%');
				log.info(
						String.format("Model Precision@1: %.2f ", confidenceMetricsEntry.getPrecisionAt1() * 100)
								+ '%');
				log.info(
						String.format("Model Recall@1: %.2f ", confidenceMetricsEntry.getRecallAt1() * 100) + '%');
				log.info(
						String.format("Model F1 score@1: %.2f ", confidenceMetricsEntry.getF1ScoreAt1() * 100) + '%');
			}
		}
	}

	public void predict(String projectId, String computeRegion, String modelId, String filePath,
			String scoreThreshold) throws IOException {

		// Instantiate client for prediction service.
		PredictionServiceClient predictionClient = PredictionServiceClient.create();

		// Get the full path of the model.
		ModelName name = ModelName.of(projectId, computeRegion, modelId);

		// Read the image and assign to payload.
		ByteString content = ByteString.copyFrom(Files.readAllBytes(Paths.get(filePath)));
		Image image = Image.newBuilder().setImageBytes(content).build();
		ExamplePayload examplePayload = ExamplePayload.newBuilder().setImage(image).build();

		// Additional parameters that can be provided for prediction e.g. Score
		// Threshold
		Map<String, String> params = new HashMap<>();
		if (scoreThreshold != null) {
			params.put("score_threshold", scoreThreshold);
		}
		// Perform the AutoML Prediction request
		PredictResponse response = predictionClient.predict(name, examplePayload, params);

		log.info("Prediction results:");
		for (AnnotationPayload annotationPayload : response.getPayloadList()) {
			log.info("Predicted class name :" + annotationPayload.getDisplayName());
			log.info("Predicted class score :" + annotationPayload.getClassification().getScore());
		}
	}
}
