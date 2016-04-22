/**
 * 
 */
package br.com.cams7.sys.service;

import br.com.cams7.sys.entity.AbstractEntity;
import br.com.cams7.sys.repository.BaseRepository;

/**
 * Interface comum as classes Service
 * 
 * @author cesar
 *
 */
public interface BaseService<E extends AbstractEntity> extends BaseRepository<E> {

}
