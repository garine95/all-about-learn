package garine.learn.activity.service.api;

import garine.learn.user.api.IUserQueryService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${practice.user.core.url:}", name = "${practice.user.core.serviceid}")
public interface userQueryServiceRemote extends IUserQueryService {
}
