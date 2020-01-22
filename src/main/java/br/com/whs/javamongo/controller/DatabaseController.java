package br.com.whs.javamongo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClient;

import br.com.whs.javamongo.util.MongoUtil;

@RestController
@RequestMapping("/database")
@Configuration
public class DatabaseController {
	
	@Value("${data.mongodb.uri}")
    private String mongoUri;
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<String> listDatabaseNames() {
		System.out.println("mongoUri: "+mongoUri);
        return MongoUtil.listDatabaseNames(MongoUtil.loadMongoClient(mongoUri));
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Map<String,Object> map) {
		System.out.println("Creating... "+map.get("databaseName"));
		MongoUtil.loadMongoDatabase(MongoUtil.loadMongoClient(mongoUri), (String)map.get("databaseName"));
		MongoClient mc = MongoUtil.loadMongoClient(mongoUri);
		mc.getDatabase((String)map.get("databaseName"));
    }
	
/*	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Student studentEntry) {
		service.create(studentEntry);
    }
	
	@RequestMapping(value = "/find/{idStudent}", method = RequestMethod.GET)
    public Student findByID(@PathVariable Long idStudent) {
        return service.findByID(idStudent);
    }
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<Student> list() {
        return service.list();
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public Student update(@RequestBody @Valid Student studentEntry) {
		return service.update(studentEntry);
    }
	
	@RequestMapping(value = "/delete/{idStudent}", method = RequestMethod.GET)
    public void delete(@PathVariable Long idStudent) {
		service.delete(idStudent);
    }
    */
}