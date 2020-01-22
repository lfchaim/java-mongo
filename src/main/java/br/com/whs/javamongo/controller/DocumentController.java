package br.com.whs.javamongo.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.whs.javamongo.util.MongoUtil;

@RestController
@RequestMapping("/document")
public class DocumentController {

	@RequestMapping(value = "/create/{database}/{collection}", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("database") String database, @PathVariable("collection") String collection , @RequestBody @Valid Map<String,Object> map) {
		MongoDatabase md = MongoUtil.loadMongoDatabase(MongoUtil.loadMongoClient(), database);
		MongoCollection mc = MongoUtil.loadMongoCollection(md, collection);
		MongoUtil.insertOne(mc, map);
    }
	
}
