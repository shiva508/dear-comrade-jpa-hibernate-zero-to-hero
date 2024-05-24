package com.comrade;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DearComradeJpaHibernateZeroToHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DearComradeJpaHibernateZeroToHeroApplication.class, args);
	}

//	@Bean
//	public ApplicationRunner runSomeTask(TopicRepository topicRepository, TopicTypeRepository topicTypeRepository){
//		return args -> {
//			Topic topic = new Topic();
//			topic.setName("Java");
//			topicRepository.save(topic);
//			TopicType topicType = new TopicType();
//			topicType.setName("What is java latest version");
//			topicTypeRepository.save(topicType);
//		};
//	}

}
