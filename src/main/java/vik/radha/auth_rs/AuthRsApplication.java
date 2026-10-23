package vik.radha.auth_rs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@SpringBootApplication
public class AuthRsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthRsApplication.class, args);
	}

}

//create TABLE  dogs(id int not null, name varchar(50) not null, owner varchar(50) not null, primary key (id))
@Table(name="dogs")
record Dog(@Id int id, String name, String owner){}

interface DogsRepository extends ListCrudRepository<Dog, Integer> {
	List<Dog> findByOwner(String name) ;
}

@RestController
class DogsController {
	private final DogsRepository dogsRepository;

	DogsController(DogsRepository dogsRepository) {
		this.dogsRepository = dogsRepository;
	}

	@GetMapping("/dogs")
	public List<Dog> findAll() {
		return dogsRepository.findAll();
	}
	@GetMapping("/dogs/owner")
	public List<Dog> findAll(Principal principal) {
		return dogsRepository.findByOwner(principal.getName());
	}
}


//CREATE USER 'dogs'@'%' IDENTIFIED BY 'dogs';
//GRANT ALTER ON dogs.dogs TO 'dogs'@'%';
//GRANT CREATE ON dogs.dogs TO 'dogs'@'%';
//GRANT CREATE VIEW ON dogs.dogs TO 'dogs'@'%';
//GRANT DELETE ON dogs.dogs TO 'dogs'@'%';
//GRANT DROP ON dogs.dogs TO 'dogs'@'%';
//GRANT GRANT OPTION ON dogs.dogs TO 'dogs'@'%';
//GRANT INDEX ON dogs.dogs TO 'dogs'@'%';
//GRANT INSERT ON dogs.dogs TO 'dogs'@'%';
//GRANT REFERENCES ON dogs.dogs TO 'dogs'@'%';
//GRANT SELECT ON dogs.dogs TO 'dogs'@'%';
//GRANT SHOW VIEW ON dogs.dogs TO 'dogs'@'%';
//GRANT TRIGGER ON dogs.dogs TO 'dogs'@'%';
//GRANT UPDATE ON dogs.dogs TO 'dogs'@'%';
