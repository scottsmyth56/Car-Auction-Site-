package com.fdmgroup.CarSiteSoloProject.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.CarSiteSoloProject.model.OnlineBids;
import com.fdmgroup.CarSiteSoloProject.repository.OnlineBidsRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OnlineBidServiceTest {

	
	@InjectMocks
	private OnlineBidsService mockBidService;
	
	
	@Mock
	private OnlineBids mockBid;
	
	@MockBean
	private OnlineBidsRepository mockRepo;
	
	
	private OnlineBids bid;
	
	
	@BeforeEach
	void init() {
		bid = new OnlineBids();
	}
	
	@Test
	void test_findBidsbyCarID() {
		mockBidService.findAllBidsByCarAdID(1);
		verify(mockRepo,times(1)).findAllBidsByCarAdID(1);
	}
	@Test
	void test_findWinningBidsbyCarID() {
		mockBidService.findAllBidsByCarAdID(1);
		verify(mockRepo,times(1)).findAllBidsByCarAdID(1);
	}
	@Test
	void test_saveBid() {
		mockBidService.saveBid(bid);
		verify(mockRepo,times(1)).save(bid);
	}
	@Test
	void test_findBidbyID() {
		mockBidService.findBidByID(1);
		verify(mockRepo,times(1)).findById(1);
	}
	@Test
	void test_removeBid() {
		mockBidService.removeBid(bid);
		verify(mockRepo,times(1)).delete(bid);
	}
	
	
}
