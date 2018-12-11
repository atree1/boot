package org.oos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.cloud.automl.v1beta1.AnnotationPayload;
import com.google.cloud.automl.v1beta1.ExamplePayload;
import com.google.cloud.automl.v1beta1.Image;
import com.google.cloud.automl.v1beta1.ModelName;
import com.google.cloud.automl.v1beta1.PredictResponse;
import com.google.cloud.automl.v1beta1.PredictionServiceClient;
import com.google.protobuf.ByteString;

import lombok.extern.java.Log;

@Service
@Log
public class AutoMlService {
	
	public List<String> predict(String projectId, String computeRegion, String modelId, String filePath,
			String scoreThreshold) throws IOException {

		// Instantiate client for prediction service.
		PredictionServiceClient predictionClient = PredictionServiceClient.create();
		List<String> list=new ArrayList<>();
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
			list.add(annotationPayload.getDisplayName());
			
			log.info("Predicted class score :" + annotationPayload.getClassification().getScore());
		}
		
		return list;
	}
}
