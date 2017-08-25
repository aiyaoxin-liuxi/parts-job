package com.zhl.job.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.job.dao.IRegionDao;
import com.zhl.job.pojo.Region;
import com.zhl.job.service.IRegionService;

@Service("regionService")
public class RegionService implements IRegionService {
	
	@Resource
	private IRegionDao regionDao;

	@Override
	public Map<String, Object> queryAll() {
		
		Map<String, Object> cMap = new LinkedHashMap<String, Object>();
		Map<String, Object> aMap = new LinkedHashMap<String, Object>();
		
		List<Region> list = regionDao.queryRegionAll();
		Map<String, Object> aaMap = null;
		for(Region region : list){
			if("2".equals(region.getRegionType())){
				// 市
				cMap.put(region.getRegionId(), region.getRegionName());
			} else if("3".equals(region.getRegionType())){
				// 区
				if(aMap.containsKey(region.getParentId())){
					aaMap = (Map<String, Object>) aMap.get(region.getParentId());
					aaMap.put(region.getRegionId(), region.getRegionName());
					aMap.put(region.getParentId(), aaMap);
				} else {
					aaMap = new LinkedHashMap<String, Object>();
					aaMap.put(region.getRegionId(), region.getRegionName());
					aMap.put(region.getParentId(), aaMap);
				}
			}
		}
		
		Map<String, Object> allMap = new HashMap<String, Object>();
		allMap.put("cityMap", cMap);
		allMap.put("areaMap", aMap);
		
		return allMap;
	}
	

}
