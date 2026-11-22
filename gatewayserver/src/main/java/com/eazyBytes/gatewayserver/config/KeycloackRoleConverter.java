package com.eazyBytes.gatewayserver.config;

import java.util.*;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;



public class KeycloackRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert(Jwt source) {
	Map<String,Object> releamAccess = (Map<String, Object>) source.getClaims().get("realm_access");
	if(releamAccess==null || releamAccess.isEmpty())
	{
		return new ArrayList<>();
	}
	Collection<GrantedAuthority> returnValue = ((java.util.List<String>) releamAccess.get("roles")).stream().map(rolename -> "ROLE_"+ rolename)
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	
	return returnValue;
	
	}

}
