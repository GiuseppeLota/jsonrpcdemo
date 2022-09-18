package com.martinbohm.demo.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.martinbohm.demo.dto.Advice;
import com.martinbohm.demo.dto.AdviceResult;
import com.martinbohm.demo.entities.AdviceRequest;
import com.martinbohm.demo.entities.AdviceResponse;
import com.martinbohm.demo.externalProviders.AdviceProvider;
import com.martinbohm.demo.operations.impl.AdviceServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdviceServiceImplIntegrationTests {

	private static final String TEST_ADVICE = "TEST_ADVICE";
	private static final String TEST_TOPIC = "TEST_TOPIC";

	@InjectMocks
	private AdviceServiceImpl adviceService;

	@Mock
	private AdviceProvider adviceProvider;

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

	private List<Advice> generateAdviceList() {
		List<Advice> advices = new ArrayList<>();
		advices.add(new Advice(1, TEST_ADVICE, new Date()));
		return advices;
	}

}
