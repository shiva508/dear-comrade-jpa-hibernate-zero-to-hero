package com.comrade;


import com.comrade.entity.NewsEntity;
import com.comrade.entity.OpinionEntity;
import com.comrade.model.NewsModel;
import com.comrade.model.OpinionModel;
import com.comrade.service.NewsService;
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

	//@Bean
	public ApplicationRunner runSomeTask(NewsService newsService){
		return args -> {
			List<NewsModel> newsEntities = IntStream.range(0, 100).boxed().map(count -> {
				NewsModel newsEntity = new NewsModel();
				newsEntity.setNewsTitle(String.format("KCR promises that BRS will live up to people mandate %s", count));
				newsEntity.setNewsAuthor(String.format("Shiva_%s", count));
				newsEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				newsEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
				List<OpinionModel> opinionEntities = IntStream.range(0, count + 1).boxed().map(childCount -> {
					OpinionModel opinionEntity = new OpinionModel();
					opinionEntity.setOpinionTitle(String.format("We will keep questioning till all promises are fulfilled %s", childCount));
					opinionEntity.setOpinionDesc(String.format("As BRS party came back to ruling by making big promises. We congratulate them and we accept we last people confidence %s", childCount));
					opinionEntity.setLeaderName(String.format("Jana Ready %s", childCount));
					return opinionEntity;
				}).toList();
				newsEntity.setOpinions(opinionEntities);
				return newsEntity;
			}).toList();

			newsEntities.forEach(newsEntity -> {
				newsService.save(newsEntity);
			});

			List<NewsEntity> savedNewsEntities = newsService.fetchAll();
			System.out.println(savedNewsEntities);
		};
	}

}
