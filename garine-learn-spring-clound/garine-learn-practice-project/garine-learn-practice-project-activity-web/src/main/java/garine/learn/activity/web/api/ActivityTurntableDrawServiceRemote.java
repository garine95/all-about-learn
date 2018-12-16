package garine.learn.activity.web.api;

import garine.learn.activity.api.draw.ActivityTurntableDrawService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${practice.activity.service.url:}", name = "${practice.activity.service.serviceid}")
public interface ActivityTurntableDrawServiceRemote extends ActivityTurntableDrawService {
}
