package com.sgmw.sales.app.sample.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.sgmw.sales.app.sample.domain.ActivitySignUp;
import com.sgmw.sales.app.sample.service.ActivitySignUPService;
import com.sgmw.sales.app.sample.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class ActivitySignUpResource {
	private final Logger log = LoggerFactory.getLogger(ActivitySignUpResource.class);
	
	private static final String ENTITY_NAME = "signup";
	
	@Autowired
	private ActivitySignUPService asus;
	
	@ApiOperation("创建报名")
	@PostMapping("/signup")
    @Timed
    public ResponseEntity<String> createSignUp(@Valid @RequestBody ActivitySignUp ActivitySignUp) throws URISyntaxException {
        log.debug("REST request to save Vechile : {}", ActivitySignUp);
        if (ActivitySignUp.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new vechile cannot already have an ID")).body(null);
        }
        int result = asus.save(ActivitySignUp);
        return ResponseEntity.created(new URI("/api/vechiles/" + result))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result)))
            .body("ok");
    }

	@ApiOperation("更新报名数据")
    @PutMapping("/signup")
    @Timed
    public ResponseEntity<String> updateSignUp(@Valid @RequestBody ActivitySignUp ActivitySignUp) throws URISyntaxException {
        log.debug("REST request to update Vechile : {}", ActivitySignUp);
        if (ActivitySignUp.getId() == null) {
            return createSignUp(ActivitySignUp);
        }
        int result = asus.update(ActivitySignUp);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, String.valueOf(result)))
            .body("ok");
    }

    
//    @GetMapping("/signup")
//    @Timed
//    public ResponseEntity<List<ActivitySignUp>> getAllSignUps(@ApiParam Pageable pageable) {
////        log.debug("REST request to get a page of Vechiles");
////        Page<ActivitySignUp> page = asus.findAll(pageable);
////        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vechiles");
////        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    	return null;
//    }

	@ApiOperation("根据ID获取报名")
    @GetMapping("/signup/{id}")
    @Timed
    public ResponseEntity<ActivitySignUp> getSignUp(@PathVariable Long id) {
        log.debug("REST request to get Vechile : {}", id);
        ActivitySignUp ActivitySignUp = asus.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ActivitySignUp));
    }

	@ApiOperation("根据ID删除报名")
    @DeleteMapping("/signup/{id}")
    @Timed
    public ResponseEntity<Void> deleteSignUp(@PathVariable Long id) {
        log.debug("REST request to delete Vechile : {}", id);
        asus.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
