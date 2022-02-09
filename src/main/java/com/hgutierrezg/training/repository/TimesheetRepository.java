package com.hgutierrezg.training.repository;

import com.hgutierrezg.training.model.TimesheetEntity;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
@Transactional
public class TimesheetRepository {

    private final SessionFactory sessionFactory;

    public Optional<TimesheetEntity> getById(Long id) {
        if (id != null) {
            // With javax persistence api since criteria query is deprecated from hibernate 5
            TimesheetEntity timesheetEntity = getCurrentSession().find(TimesheetEntity.class, id);
            return Optional.of(timesheetEntity);
        }
        return Optional.empty();
    }

    public List<TimesheetEntity> getAll() {
        //HQL query
        return getCurrentSession().createQuery("SELECT timesheetEntity FROM TimesheetEntity timesheetEntity", TimesheetEntity.class).getResultList();
    }

    public void saveEntity(TimesheetEntity entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void deleteEntity(Long id) {
        Optional<TimesheetEntity> optionalTimesheetEntity =  getById(id);
        optionalTimesheetEntity.ifPresent(timesheetEntity -> getCurrentSession().delete(timesheetEntity));
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
