/**
 * 
 */
package br.com.cams7.app.service;

import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.repository.BaseRepository;

/**
 * Interface comum as classes Service
 * 
 * @author cesar
 *
 */
public interface BaseService<E extends AbstractEntity> extends BaseRepository<E> {

}
