package io.github.ringoyungpo.filecloud;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author ringo
 */
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "files", path = "files")
public interface FileRepository extends PagingAndSortingRepository<File, Long> {
}
