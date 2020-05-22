package io.github.ringoyungpo.filecloud;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author ringo
 */
@RepositoryRestResource(collectionResourceRel = "files", path = "files")
public interface FileRepository extends PagingAndSortingRepository<File, Long> {
}
