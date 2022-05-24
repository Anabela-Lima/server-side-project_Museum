//package com.example.serversideproject_museum;
//
//import com.example.serversideproject_museum.model.Artefact;
//import com.example.serversideproject_museum.model.Exhibit;
//import com.example.serversideproject_museum.model.Museum;
//import com.example.serversideproject_museum.repository.ArtefactRepository;
//import com.example.serversideproject_museum.repository.ExhibitRepository;
//import com.example.serversideproject_museum.repository.MuseumRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//class ServerSideProjectMuseumApplicationTests {
//
//	@Autowired
//	ArtefactRepository artefactRepository;
//
//	@Autowired
//	ExhibitRepository exhibitRepository;
//
//	@Autowired
//	MuseumRepository museumRepository;
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	public void dataSqlLoadsArtefacts(){
//		List<Artefact> found = artefactRepository.findAll();
//		assertEquals(100,found.size());
//	}
//
//	@Test
//	public void dataSqlLoadExhibits(){
//		List<Exhibit> found = exhibitRepository.findAll();
//		assertEquals(27,found.size());
//	}
//
//	@Test
//	public void dataSqlLoadMuseums(){
//		List<Museum> found = museumRepository.findAll();
//		assertEquals(20,found.size());
//	}
//
//	@Test
//	public void canFindByCountry(){
//		Artefact found = artefactRepository.findByCountry(){
//
//		}
//	}
//
//}
