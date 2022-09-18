package com.martinbohm.demo.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.martinbohm.demo.dto.Advice;
import com.martinbohm.demo.dto.AdviceResult;
import com.martinbohm.demo.dto.Message;
import com.martinbohm.demo.entities.AdviceRequest;
import com.martinbohm.demo.entities.AdviceResponse;
import com.martinbohm.demo.externalProviders.AdviceProvider;
import com.martinbohm.demo.operations.impl.AdviceServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdviceServiceImplIntegrationTests {

	private static final String SEVERE_TYPE = "SEVERE_TYPE";
	private static final String EXTERNAL_API_ERROR = "EXTERNAL_API_ERROR";
	private static final String TEST_ADVICE = "TEST_ADVICE";
	private static final String TEST_TOPIC = "TEST_TOPIC";

	@InjectMocks
	private AdviceServiceImpl adviceService;

	@Mock
	private AdviceProvider adviceProvider;

	@Test
	void invalidRequest_null_topic() {

		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			adviceService.giveMeAdvice(new AdviceRequest());
		});

		Assertions.assertEquals("Topic should not be empty or blank", thrown.getMessage());
	}

	@Test
	void invalidRequest_empty_topic() {

		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			AdviceRequest request = new AdviceRequest();
			request.setTopic("");
			adviceService.giveMeAdvice(request);
		});

		Assertions.assertEquals("Topic should not be empty or blank", thrown.getMessage());
	}

	@Test
	void invalidRequest_amount_less_than_zero() {

		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			AdviceRequest request = new AdviceRequest();
			request.setTopic(TEST_TOPIC);
			request.setAmount(-2);
			adviceService.giveMeAdvice(request);
		});

		Assertions.assertEquals("Amount should be more than 0", thrown.getMessage());
	}

	@Test
	void validRequest_noDataReturned() {

		when(adviceProvider.listAdvices(any(String.class))).thenReturn(generateErrorAdviceResult());

		AdviceRequest request = new AdviceRequest();
		request.setTopic(TEST_TOPIC);

		AdviceResponse response = adviceService.giveMeAdvice(request);

		assertTrue(response.getErrorMessage().equals(EXTERNAL_API_ERROR));
		assertTrue(response.getErrorType().equals(SEVERE_TYPE));
	}

	@Test
	void validRequest_someDataReturned() {

		when(adviceProvider.listAdvices(any(String.class))).thenReturn(generateAdviceResult());

		AdviceRequest request = new AdviceRequest();
		request.setTopic(TEST_TOPIC);

		AdviceResponse response = adviceService.giveMeAdvice(request);

		assertTrue(response.getAdviceList().size() == 1);
		assertTrue(response.getAdviceList().stream().allMatch(it -> it.equals(TEST_ADVICE)));
	}

	private AdviceResult generateAdviceResult() {
		AdviceResult adviceResult = new AdviceResult();
		adviceResult.setSlips(generateAdviceList());
		return adviceResult;
	}

	private AdviceResult generateErrorAdviceResult() {
		AdviceResult adviceResult = new AdviceResult();
		Message message = new Message();
		message.setText(EXTERNAL_API_ERROR);
		message.setType(SEVERE_TYPE);
		adviceResult.setMessage(message);
		return adviceResult;
	}

	private List<Advice> generateAdviceList() {
		List<Advice> advices = new ArrayList<>();
		advices.add(new Advice(1, TEST_ADVICE, new Date()));
		return advices;
	}

}
