package com.comrade;

import com.comrade.entity.Location;
import com.comrade.entity.Order;
import com.comrade.repository.OrderRepository;
import com.comrade.service.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class DearComradeJpaHibernateZeroToHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DearComradeJpaHibernateZeroToHeroApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(OrderRepository orderRepository,
											   @Qualifier("orderService") OrderService orderService){
		return args -> {

			if (false){
				var input =	IntStream.range(1, 20000).mapToObj(value -> Order.builder()
						.orderName("Biryani_"+value)
						.price(200.00d)
						.offer(20.00d)
						.destinationLocation(Location.builder().street("Church Street").village("Thalla Malkapuram").build())
						.originLocation(Location.builder().street("Church Street").village("Thalla Malkapuram").build())
						.build()).collect(Collectors.toList());
				orderRepository.saveAll(input);
			}
			orderService.update();
		};
	}

}
