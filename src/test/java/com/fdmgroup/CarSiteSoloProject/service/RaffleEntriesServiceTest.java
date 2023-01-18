package com.fdmgroup.CarSiteSoloProject.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.CarSiteSoloProject.model.RaffleEntries;
import com.fdmgroup.CarSiteSoloProject.repository.RaffleEntriesRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RaffleEntriesServiceTest {

	@InjectMocks
	private RaffleEntriesService raffleServiceMock;

	@Mock
	private RaffleEntries raffleEntryMock;

	@MockBean
	private RaffleEntriesRepository mockRepo;

	private RaffleEntries raffleEntry;
	private RaffleEntries raffleEntry2;
	private List<RaffleEntries> list;
	

	@BeforeEach
	void init() {
		raffleEntry = new RaffleEntries();
		raffleEntry2 = new RaffleEntries(1, 1,1);
		list = new ArrayList<>();
		list.add(raffleEntry2);
	}

	@Test
	void test_saveRaffle() {
		raffleServiceMock.save(raffleEntry);
		verify(mockRepo, times(1)).save(raffleEntry);
	}

	@Test
	void test_findAllByDealer() {
		raffleServiceMock.findAllByDealerID(1);
		verify(mockRepo, times(1)).findAllByDealerID(1);
	}

	@Test
	void test_findWinner() {
		//Random rand = new Random();
		raffleServiceMock.findWinner(list);
		//verify(list).get(rand.nextInt(list.size()));
	}

}
