package garine.learn.user.sso.api;

import garine.learn.user.api.IUserQueryService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${practice.user.core.url:}", name = "${practice.user.core.serviceid}")
public interface UserQueryServiceRemote extends IUserQueryService {
}
