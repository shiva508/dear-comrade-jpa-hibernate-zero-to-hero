package com.comrade;

import com.comrade.entity.NewsEntity;
import com.comrade.model.NewsModel;
import com.comrade.model.OpinionModel;
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

@SpringBootApplication
@Slf4j
public class DearComradeJpaHibernateZeroToHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DearComradeJpaHibernateZeroToHeroApplication.class, args);
	}

	@Bean
	public ApplicationRunner runSomeTask(NewsService newsService, OpinionService opinionService){
		return args -> {
			List<NewsModel> newsEntities = IntStream.range(0, 1).boxed().map(count -> {
				NewsModel newsEntity = new NewsModel();
				newsEntity.setNewsTitle(String.format("KCR promises that BRS will live up to people mandate %s", count));
				newsEntity.setNewsAuthor(String.format("Shiva_%s", count));
				newsEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				newsEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
				return newsEntity;
			}).toList();

			newsEntities.forEach(newsEntity -> {
				NewsModel save = newsService.save(newsEntity);
				List<OpinionModel> opinionEntities = IntStream.range(0, 10).boxed().map(childCount -> {
					OpinionModel opinionEntity = new OpinionModel();
					opinionEntity.setOpinionTitle(String.format("We will keep questioning till all promises are fulfilled %s", childCount));
					opinionEntity.setOpinionDesc(String.format("As BRS party came back to ruling by making big promises. We congratulate them and we accept we last people confidence %s", childCount));
					opinionEntity.setLeaderName(String.format("Jana Ready %s", childCount));
					opinionEntity.setNewsId(save.getNewsId());
					return opinionEntity;
				}).toList();
				List<OpinionModel> opinionModels = opinionEntities.stream().map(opinionService::save).toList();
				System.out.println(opinionModels);
			});

			List<NewsEntity> savedNewsEntities = newsService.fetchAll();
			System.out.println(savedNewsEntities);
		};
	}

}
