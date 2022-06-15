package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // TODO: check children records before deleting or find a solution to deleted including the children records.
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}",  method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        // PUT expects all fields to be present, otherwise, it will replace the missing fields with NULL.
        // PATCH would only replace the specified field(s).
        // TODO: Add validation to all fields passed in. Return 400 error for a bad payload. Add also validation if the session passed in exists.
        Session existingSession = sessionRepository.getOne((id));
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

}
