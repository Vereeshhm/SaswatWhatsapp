//package com.example.SaswatWhatsapp.ServiceImpl;
//
//import org.hibernate.boot.MetadataBuilder;
//import org.hibernate.boot.model.TypeContributions;
//import org.hibernate.boot.model.TypeContributor;
//import org.hibernate.service.ServiceRegistry;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
//@Configuration
//public class HibernateConfig implements TypeContributor {
//
//    @Override
//    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
//        typeContributions.contributeType(new JsonBinaryType());
//    }
//
////    @Bean
////    public HibernateJpaSessionFactoryBean sessionFactory() {
////        return new HibernateJpaSessionFactoryBean();
////    }
//
//    @Bean
//    public TypeContributor typeContributor() {
//        return new HibernateConfig();
//    }
//
//    @Bean
//    public MetadataBuilder applyTypes(MetadataBuilder metadataBuilder) {
//        metadataBuilder.applyTypes(this);
//        return metadataBuilder;
//    }
//
//	
//	
//}