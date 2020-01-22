package br.com.whs.javamongo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoDatabase;

import br.com.whs.javamongo.util.MongoUtil;

@RestController
@RequestMapping("/collection")
public class CollectionController {
	
	@Value("${data.mongodb.uri}")
    private String mongoUri;
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<Map<String,Object>> listDatabaseNames() {
		List<Map<String,Object>> ret = new ArrayList<Map<String,Object>>();
		List<String> listDatabase = MongoUtil.listDatabaseNames(MongoUtil.loadMongoClient(mongoUri));
		for( int i = 0; i < listDatabase.size(); i++ ) {
			Map<String,Object> map = new LinkedHashMap<String,Object>();
			map.put("databaseName", listDatabase.get(i));
			MongoDatabase mongoDatabase = MongoUtil.loadMongoDatabase(MongoUtil.loadMongoClient(mongoUri), listDatabase.get(i));
			map.put("listCollection", MongoUtil.listCollection(mongoDatabase));
			ret.add(map);
		}
        return ret;
	}
	
	@RequestMapping(value = "/findByDatabaseName", method = RequestMethod.GET)
    public List<String> listDatabaseNames(@RequestParam("databaseName") String databaseName) {
		MongoDatabase mongoDatabase = MongoUtil.loadMongoDatabase(MongoUtil.loadMongoClient(mongoUri), databaseName);
        return MongoUtil.listCollection(mongoDatabase);
	}

}
