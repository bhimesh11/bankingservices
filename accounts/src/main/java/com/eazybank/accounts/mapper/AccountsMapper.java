package com.eazybank.accounts.mapper;

import com.eazybank.accounts.dto.AccountsDTO;
import com.eazybank.accounts.entity.accounts;

public class AccountsMapper {

	public static AccountsDTO mapToAccountDTO(accounts accounts, AccountsDTO accountsDTO) {
		accountsDTO.setAccount_number(accounts.getAccount_number());
		accountsDTO.setAccount_type(accounts.getAccount_type());
		accountsDTO.setBranch_address(accounts.getBranch_address());

		return accountsDTO;
	}

	public static accounts mapToAccounts(AccountsDTO accountsDTO, accounts accounts) {
		accounts.setAccount_number(accountsDTO.getAccount_number());
		accounts.setAccount_type(accountsDTO.getAccount_type());
		accounts.setBranch_address(accountsDTO.getBranch_address());
		return accounts;
	}
}
