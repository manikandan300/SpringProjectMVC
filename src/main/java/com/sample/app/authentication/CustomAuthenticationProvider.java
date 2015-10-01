package com.sample.app.authentication;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



import org.springframework.stereotype.Component;




public class CustomAuthenticationProvider implements AuthenticationProvider {



	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
	/*	MemberService memberservice= (MemberService) SpringApplicationContext.getBean("memberservice") ;
		AuditLogService auditlogService= (AuditLogService) SpringApplicationContext.getBean("auditlogservice") ;

		try {
			AuditLogModel auditLogModel = AuditLogModel.getInstance(AuditLog.ActivityComponent.SYSTEM,  AuditLog.ActivityType.OTHERS,
					AuditLog.LOGIN, AuditLog.LOGIN_PARAMS, new String[] {"loginid", username});

			auditlogService.add(auditLogModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MemberModel member=  memberservice.validateMemberLogin(username);

		if(member!=null)
		{
			if(member.getPassword()!=null && 
					password!=null &&
					member.getPassword().length()>0&&
					EncryptorUtil.decrypt(member.getPassword()).equals(password) )
			{


				if(member.getStatus().equalsIgnoreCase(Constants.RecordStatus.ACTIVE.toString()))
				{
					List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
					
					if(member.getRoles()==null || member.getRoles().trim().length()==0)
						throw  new BadCredentialsException ("Role Not Defined");
					
					String[] roles=member.getRoles().split(",");
					
					for(String strroles:roles)
						grantedAuths.add(new SimpleGrantedAuthority(strroles));
					
					memberservice.updateMemberLogin(member.getMemberid(), new Date());
					
					return new UsernamePasswordAuthenticationToken(member, password, grantedAuths);
				}
				else
					throw  new AccountExpiredException("Member Not Active");

			}
			else
				throw  new BadCredentialsException("Invalid Member /Password");

		}
		else
			throw  new BadCredentialsException("Member Not Found");*/
		return authentication;

	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return arg0.equals(UsernamePasswordAuthenticationToken.class);
	}

}
