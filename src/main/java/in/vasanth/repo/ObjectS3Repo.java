package in.vasanth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vasanth.entity.MapKeyWithData;

public interface ObjectS3Repo extends JpaRepository<MapKeyWithData, Integer>{

}
