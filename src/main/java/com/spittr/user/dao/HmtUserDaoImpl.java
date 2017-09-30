package com.spittr.user.dao;

import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.user.model.HMTUser;

@Repository
public class HMTUserDaolImpl extends BaseDaoHibernate5<HMTUser> implements HMTUserDao{

}
