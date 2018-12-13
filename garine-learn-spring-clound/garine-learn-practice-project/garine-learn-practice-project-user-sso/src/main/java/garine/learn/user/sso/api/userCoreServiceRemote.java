package garine.learn.user.sso.api;

import garine.learn.user.api.IUserCoreService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${practice.user.core.url:}", serviceId = "${practice.user.core.serviceid}")
public interface userCoreServiceRemote extends IUserCoreService {
}
