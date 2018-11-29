

//TODO leave this (implement at some point)
//package com.common.atmochat.db.changelogs;
//
//import com.github.mongobee.changeset.ChangeLog;
//import com.github.mongobee.changeset.ChangeSet;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import java.util.*;
//
///**
// * Created by eleonorakocharyan on 2/1/18.
// */
//@ChangeLog
//public class DatabaseChangelog {
////    @ChangeSet(order = "001", id = "addTestCustemFields", author = "Arthurh")
////    public void someChange1(MongoTemplate mongoTemplate) {
////        System.out.println(mongoTemplate);
////    }
////
////    @ChangeSet(order = "002", id = "addSpToolCustomFields", author = "Sergey")
////    public void addSpToolCustomFields(MongoTemplate mongoTemplate) {
////
////        List<Integer> books = Arrays.asList(27464, 747854);
////        DBObject person = new BasicDBObject("_id", "jo")
////                .append("name", "Jo Bloggs")
////                .append("address", new BasicDBObject("street", "123 Fake St")
////                        .append("city", "Faketon")
////                        .append("state", "MA")
////                        .append("zip", 12345))
////                .append("books", books);
////    }
////
////    @ChangeSet(order = "003", id = "addServiceProviderCustomFields", author = "Sergey")
////    public void addServiceProviderCustomFields(MongoTemplate mongoTemplate) {
////
////        //Export Declaration
////        Map<String, String> languageTextMap = getItemMap("Export Declaration", "საექსპორტო დეკლარაცია");
////        List<CustomFieldItemIn> fieldItems = new ArrayList<>();
////
////        updateCustomFieldItem(fieldItems, "Shipper", "გადამზიდავი");
////        updateCustomFieldItem(fieldItems, "Transportit", "Transportit");
////
////        CustomFieldIn customFieldIn = getCustomFieldIn("export_declaration", "Export Declaration", CustomFieldType.LIST, CustomFieldEntity.SERVICE_PROVIDER, 1, 1, new CustomFieldValidation(), fieldItems);
////        addCustomField(mongoTemplate, customFieldIn, languageTextMap);
////    }
////
////    @ChangeSet(order = "004", id = "updateOrderFieldsTranslations", author = "Sergey")
////    public void updateOrderFieldsTranslations(MongoTemplate mongoTemplate) {
////
////        updateMessage(mongoTemplate, "address_to", CustomFieldEntity.ORDER.name(), "ge", "სად");
////        updateMessage(mongoTemplate, "address_from", CustomFieldEntity.ORDER.name(), "ge", "საიდან");
////
////
////        updateMessage(mongoTemplate, "start_date", CustomFieldEntity.ORDER.name(), "ge", "Date of Pick Up");
////        updateMessage(mongoTemplate, "end_date", CustomFieldEntity.ORDER.name(), "ge", "Date of delivery");
////
////
////    }
////
//////    @ChangeSet(order = "005", id = "addServiceProviderFields", author = "Sergey")
//////    public void addServiceProviderFields(MongoTemplate mongoTemplate) {
//////
//////        //First Name
//////        Map<String, String> languageTextMap = getItemMap("First Name", "სახელი");
//////        List<CustomFieldItemIn> fieldItems = new ArrayList<>();
//////        CustomFieldIn customFieldIn = getCustomFieldIn("first_name", "First Name", CustomFieldType.SP_FIRSTNAME, CustomFieldEntity.SERVICE_PROVIDER, 1, 1, validation(true), fieldItems);
//////        addCustomField(mongoTemplate, customFieldIn, languageTextMap);
//////
//////        //Last Name
//////        languageTextMap = getItemMap("Last Name", "გვარი");
//////        fieldItems = new ArrayList<>();
//////        customFieldIn = getCustomFieldIn("last_name", "Last Name", CustomFieldType.SP_LASTNAME, CustomFieldEntity.SERVICE_PROVIDER, 1, 1, validation(true), fieldItems);
//////        addCustomField(mongoTemplate, customFieldIn, languageTextMap);
//////
//////        //Phone
//////        languageTextMap = getItemMap("Phone", "გვარი");
//////        fieldItems = new ArrayList<>();
//////        customFieldIn = getCustomFieldIn("phone", "Phone", CustomFieldType.SP_PHONE, CustomFieldEntity.SERVICE_PROVIDER, 1, 1, validation(true), fieldItems);
//////        addCustomField(mongoTemplate, customFieldIn, languageTextMap);
//////
//////        //Description
//////        languageTextMap = getItemMap("Description", "გვარი");
//////        fieldItems = new ArrayList<>();
//////        customFieldIn = getCustomFieldIn("description", "Description", CustomFieldType.SP_DESCRIPTION, CustomFieldEntity.SERVICE_PROVIDER, 1, 1, validation(false), fieldItems);
//////        addCustomField(mongoTemplate, customFieldIn, languageTextMap);
//////
//////        //Address
//////        languageTextMap = getItemMap("Address", "");
//////        fieldItems = new ArrayList<>();
//////        customFieldIn = getCustomFieldIn("address", "Address", CustomFieldType.SP_ADDRESS, CustomFieldEntity.SERVICE_PROVIDER, 1, 1, validation(false), fieldItems);
//////        addCustomField(mongoTemplate, customFieldIn, languageTextMap);
//////
//////        //Passport Number
//////        languageTextMap = getItemMap("Passport Number", "პასპორტის ნომერი");
//////        fieldItems = new ArrayList<>();
//////        customFieldIn = getCustomFieldIn("passport_number", "Passport Number", CustomFieldType.SP_PASSPORT_NUMBER, CustomFieldEntity.SERVICE_PROVIDER, 1, 1, validation(false), fieldItems);
//////        addCustomField(mongoTemplate, customFieldIn, languageTextMap);
//////
//////
//////    }
////
////
////    private void addCustomField(MongoTemplate mongoTemplate, CustomFieldIn customFieldIn, Map<String, String> languageTextMap) {
////        ObjectId productId = new ObjectId("5aa2c51a0c33065943fab28d");
////        String key = customFieldIn.getKey();
////        String entity = customFieldIn.getEntity().name();
////
////        List<CustomField> fieldList = findCustomFields(mongoTemplate, key, entity);
////
////
////        if (fieldList.size() == 0) {
////
////            Query messageQuery = Query.query(Criteria.where("product.$id").is(productId).and("key").is(key));
////            List<Messages> messagesList = mongoTemplate.find(messageQuery, Messages.class);
////            Messages messages = !CollectionUtils.isEmpty(messagesList) ? messagesList.get(0) : null;
////
////            Product product = mongoTemplate.find(Query.query(Criteria.where("_id").is(productId)), Product.class).get(0);
////
////            messages = addMessage(messages, product, key, languageTextMap, mongoTemplate);
////
////            //custom field domain
////            CustomField customFieldDomain = new CustomField();
////
////            customFieldDomain.setName(messages);
////            customFieldDomain.setProduct(product);
////            customFieldDomain.setValidation(customFieldIn.getValidation());
////            customFieldDomain.setType(customFieldIn.getType());
////            customFieldDomain.setKey(key);
////            customFieldDomain.setOrderNum(customFieldIn.getOrderNum());
////            customFieldDomain.setPage(customFieldIn.getPage());
////            customFieldDomain.setEntity(customFieldIn.getEntity());
////            for (CustomFieldItemIn it : customFieldIn.getItems()) {
////                it.setId(UUID.randomUUID().toString());
////                customFieldDomain.getItems().add(it);
////            }
////
////
////            mongoTemplate.save(customFieldDomain);
////
////        }
////
////
////    }
////
////
////    private Messages addMessage(Messages messages, Product product, String key, Map<String, String> textByLanguageMap, MongoTemplate mongoTemplate) {
////
////        if (messages == null) {
////            messages = new Messages();
////            messages.setKey(key);
////            messages.setProduct(product);
////        }
////
////        messages.getText().putAll(textByLanguageMap);
////        mongoTemplate.save(messages);
////
////        return messages;
////    }
////
////
////    private CustomFieldIn getCustomFieldIn(String key, String name, CustomFieldType type, CustomFieldEntity entity, Integer page, Integer orderNum, CustomFieldValidation validation, List<CustomFieldItemIn> items) {
////        CustomFieldIn customFieldIn = new CustomFieldIn();
////        customFieldIn.setKey(key);
////        customFieldIn.setName(name);
////        customFieldIn.setEntity(entity);
////        customFieldIn.setItems(items);
////        customFieldIn.setType(type);
////        customFieldIn.setPage(page);
////        customFieldIn.setOrderNum(orderNum);
////        customFieldIn.setValidation(validation);
////
////        return customFieldIn;
////    }
////
////    private void updateCustomFieldItem(List<CustomFieldItemIn> items, String enText, String geText) {
////        CustomFieldItemIn customFieldItemIn = new CustomFieldItemIn();
////        customFieldItemIn.setText(getItemMap(enText, geText));
////        items.add(customFieldItemIn);
////    }
////
////    private Map<String, String> getItemMap(String enText, String geText) {
////        Map<String, String> itemMap = new HashMap<>();
////        itemMap.put("en", enText);
////        itemMap.put("ge", geText);
////        return itemMap;
////    }
////
////    private CustomFieldValidation validation(boolean isRequired) {
////        CustomFieldValidation validation = new CustomFieldValidation();
////        validation.setRequired(isRequired);
////
////        return validation;
////    }
////
////    private List<CustomField> findCustomFields(MongoTemplate mongoTemplate, String key, String entity) {
////
////        ObjectId productId = new ObjectId("5aa2c51a0c33065943fab28d");
////        Query customFieldQuery = Query.query(Criteria.where("product.$id").is(productId).and("entity").is(entity).and("key").is(key));
////
////        return mongoTemplate.find(customFieldQuery, CustomField.class, "custom_fields");
////    }
////
////    private void updateMessage(MongoTemplate mongoTemplate, String key, String entity, String languageCode, String text){
////
////        List<CustomField> fieldList = findCustomFields(mongoTemplate, key, entity);
////
////        if (fieldList.size() > 0) {
////            CustomField customField= fieldList.get(0);
////
////
////            Messages messages =customField.getName();
////
////
////            messages.getText().put(languageCode, text);
////
////            mongoTemplate.save(messages);
////        }
////    }
//
//}
