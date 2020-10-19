package com.example.demo1.web.rest;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.example.demo1.entity.Note;
import com.example.demo1.repository.NoteRepository;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.List;
import java.util.Optional;

@Path("/notes")
public class NoteResource {

    private final NoteRepository repository;

    public NoteResource(NoteRepository repository) {
        this.repository = repository;
    }

    @GET
    @Produces("application/json")
    public Iterable<Note> findAll() {
        return repository.findAll();
    }

    @GET
    @Produces("application/json")
    @Path("/id/{id}")
    public Note findOne(@PathParam long id) {
        Optional<Note> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        throw new IllegalArgumentException("No Note with id " + id + " exists");
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public void delete(@PathParam long id) {
        repository.deleteById(id);
    }

    @POST
    @Transactional
    @Path("/note/{note}/owner/{owner}")
    @Produces("application/json")
    public Note create(@PathParam String note, @PathParam String owner) {
        return repository.save(new Note(note, owner));
    }

    @PUT
    @Path("/id/{id}/owner/{owner}")
    @Produces("application/json")
    public Note changeOwner(@PathParam Long id, @PathParam String owner) {
        Optional<Note> optional = repository.findById(id);
        if (optional.isPresent()) {
            Note note = optional.get();
            note.setOwner(owner);
            return repository.save(note);
        }

        throw new IllegalArgumentException("No Note with id " + id + " exists");
    }

    @GET
    @Path("/owner/{owner}")
    @Produces("application/json")
    public List<Note> findByColor(@PathParam String owner) {
        return repository.findByOwner(owner);
    }
}
