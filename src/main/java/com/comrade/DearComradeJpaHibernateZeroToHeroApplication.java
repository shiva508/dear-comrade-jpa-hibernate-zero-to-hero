package com.comrade;

import com.comrade.entity.NewsEntity;
import com.comrade.entity.PresenterEntity;
import com.comrade.model.NewsModel;
import com.comrade.model.OpinionModel;
import com.comrade.model.PresenterModel;
import com.comrade.service.NewsService;
import com.comrade.service.OpinionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class DearComradeJpaHibernateZeroToHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DearComradeJpaHibernateZeroToHeroApplication.class, args);
	}

	@Bean
	public ApplicationRunner runSomeTask(NewsService newsService, OpinionService opinionService){
		return args -> {
			List<NewsModel> newsEntities = IntStream.range(0, 10).boxed().map(count -> {
				NewsModel newsEntity = new NewsModel();
				newsEntity.setNewsTitle(String.format("KCR promises that BRS will live up to people mandate %s", count));
				newsEntity.setNewsAuthor(String.format("Shiva_%s", count));
				newsEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				newsEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
				List<PresenterModel> presenters = IntStream.range(0, 10).boxed().map(countChild -> {
					PresenterModel presenterModel = new PresenterModel();
					presenterModel.setPresenterName(String.format("Dasari_%s", countChild));
					return presenterModel;
				}).toList();
				newsEntity.setPresenters(presenters);
				return newsService.save(newsEntity);
			}).toList();
			System.out.println(newsEntities);
		};
	}

}
