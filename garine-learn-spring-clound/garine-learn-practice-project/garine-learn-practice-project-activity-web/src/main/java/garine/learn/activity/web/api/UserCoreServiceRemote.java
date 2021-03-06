package garine.learn.activity.web.api;

import garine.learn.user.api.IUserCoreService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${practice.user.core.url:}", name = "${practice.user.core.serviceid}")
public interface UserCoreServiceRemote extends IUserCoreService {
}
