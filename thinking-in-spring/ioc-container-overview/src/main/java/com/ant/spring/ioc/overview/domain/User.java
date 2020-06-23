package com.ant.spring.ioc.overview.domain;

import com.ant.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;


/**
 * <p>
 * 用户类
 * </p>
 *
 * @author Ant gxstax@163.com
 * @since 2020-01-03 09:24
 */
public class User implements BeanNameAware {

    private Long id;

    private String name;

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    private Resource configFileLocation;

    private Company company;

    private String context;
    /**
     * 当前 Bean 的名称
     */
    private transient String beanName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", city=" + city +
				", workCities=" + Arrays.toString(workCities) +
				", lifeCities=" + lifeCities +
				", configFileLocation=" + configFileLocation +
				", company=" + company +
				", context='" + context + '\'' +
				'}';
	}

	public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("马以");
        return user;
    }

    @PostConstruct
    public void init() {
		System.out.println("用户 Bean【" + beanName + "】对象初始化...");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("用户 Bean【" + beanName + "】对象销毁中...");
	}


	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}
}
