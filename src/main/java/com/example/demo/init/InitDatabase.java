package com.example.demo.init;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.example.demo.dal.AddressDao;
import com.example.demo.dal.AllergenProtocolsDao;
import com.example.demo.dal.AllergensDao;
import com.example.demo.dal.DiagnosisDao;
import com.example.demo.dal.InjectionDao;
import com.example.demo.dal.PatientDao;
import com.example.demo.dal.ProtocolDao;
import com.example.demo.dal.SessionDao;
import com.example.demo.dal.UserDao;
import com.example.demo.data.AddressEntity;
import com.example.demo.data.AllergenProtocolsEntity;
import com.example.demo.data.AllergensEntity;
import com.example.demo.data.DiagnosisEntity;
import com.example.demo.data.Gender;
import com.example.demo.data.Hmo;
import com.example.demo.data.InjectionEntity;
import com.example.demo.data.InjectionLocation;
import com.example.demo.data.PatientEntity;
import com.example.demo.data.ProtocolEntity;
import com.example.demo.data.QuestionEntity;
import com.example.demo.data.SessionEntity;
import com.example.demo.data.UserEntity;
import com.example.demo.data.UserRole;
import com.example.demo.data.VaccineStatus;
import com.example.demo.data.util.EntityFactory;

@Component
public class InitDatabase implements CommandLineRunner{

	private AddressDao addressDao;
	private AllergenProtocolsDao allergenProtocolsDao;
	private AllergensDao allergensDao;
	private DiagnosisDao diagnosisDao;
	private InjectionDao injectionDao;
	private PatientDao patientDao;
	private ProtocolDao protocolDao;
	private SessionDao sessionDao;
	private UserDao userDao;

	private EntityFactory factory;



	private static String[] ALLERGEENS = {"Acacia","Cypress", "Olive", "Oak", "Poplar", "Pecan", "Grasses", "Bermuda", "Orchard", "Rye", "jonnson", "Lamb's quar", "Pigweed", "parietaria", "Plantain", "Sage", "Russ. Thistle", "Mix Mite", "cat", "Molds mix"};


	private static String[] NORMAL_DUST_CONCENTRATION = {"5 au", "5 au", "5 au", "5 au", "50 au", "50 au", "50 au", "50 au", "500 au", "500 au", "500 au", "500 au", "500 au", "500 au", "5000 au", "5000 au", "5000 au", "5000 au"};
	private static String[] NORMAL_DUST_DOSAGE = {"0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml","0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.03 ml", "0.05 ml", "0.1 ml", "0.2 ml", "0.3 ml", "0.4 ml", "0.03 ml", "0.05 ml", "0.07 ml", "0.1 ml"};
	private static String[] NORMAL_DUST_COLOR = {"red", "red", "red", "red", "green", "green", "green", "green", "blue", "blue", "blue", "blue", "blue", "blue", "black", "black", "black", "black", };

	private static String[] CAREFUL_DUST_CONCENTRATION = {"5 au", "5 au", "5 au", "5 au", "50 au", "50 au", "50 au", "50 au", "50 au", "500 au", "500 au", "500 au", "500 au", "500 au", "500 au", "500 au", "500 au", "500 au", "500 au", "5000 au", "5000 au", "5000 au", "5000 au", "5000 au"};
	private static String[] CAREFUL_DUST_DOSAGE = {"0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.05 ml", "0.1 ml", "0.2 ml", "0.3 ml", "0.4 ml", "0.03 ml", "0.05 ml", "0.07 ml", "0.1 ml", "0.15 ml", "0.2 ml", "0.25 ml", "0.3 ml", "0.35 ml", "0.4 ml", "0.02 ml", "0.04 ml", "0.06 ml", "0.08 ml", "0.1 ml"};
	private static String[] CAREFUL_DUST_COLOR = {"red", "red", "red", "red", "green", "green", "green", "green", "green", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "black", "black", "black", "black", "black"};

	

	private static String[] NORMAL_INHALED_CONCENTRATION = {"1:10,000", "1:10,000", "1:10,000", "1:10,000", "1:1,000", "1:1,000", "1:1,000", "1:1,000", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100"};
	private static String[] NORMAL_INHALED_DOSAGE = {"0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.03 ml", "0.05 ml", "0.07 ml", "0.1 ml", "0.2 ml", "0.3 ml", "0.4 ml", "0.5 ml"};
	private static String[] NORMAL_INHALED_COLOR = {"green", "green", "green", "green", "blue", "blue", "blue", "blue", "black", "black", "black", "black", "black", "black", "black", "black"};

	private static String[] CAREFUL_INHALED_CONCENTRATION ={"1:10,000", "1:10,000", "1:10,000", "1:10,000", "1:1,000", "1:1,000", "1:1,000", "1:1,000", "1:1,000", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100", "1:100"};
	private static String[] CAREFUL_INHALED_DOSAGE = {"0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.05 ml", "0.1  ml", "0.2 ml", "0.3 ml", "0.4 ml", "0.03 ml", "0.05 ml", "0.07 ml", "0.1 ml", "0.15 ml", "0.2 ml", "0.25 ml", "0.3 ml", "0.35 ml", "0.4 ml", "0.45 ml", "0.5 ml"};
	private static String[] CAREFUL_INHALED_COLOR = {"green", "green", "green", "green", "blue", "blue", "blue", "blue", "blue", "black", "black", "black", "black", "black", "black", "black", "black", "black", "black", "black", "black"};
	
	

	private static String[] NORMAL_CAT_CONCENTRATION = {"10 BAU", "10 BAU", "10 BAU", "10 BAU", "100 BAU", "100 BAU", "100 BAU", "100 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU"};
	private static String[] NORMAL_CAT_DOSAGE = {"0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.03 ml", "0.05 ml", "0.1 ml", "0.2 ml", "0.3 ml", "0.4 ml", "0.5 ml", "0.03 ml", "0.05 ml", "0.07 ml", "0.1 ml", "0.2 ml", "0.3 ml"};
	private static String[] NORMAL_CAT_COLOR = {"red", "red", "red", "red", "green", "green", "green", "green", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "black", "black", "black", "black", "black", "black"};

	private static String[] CAREFUL_CAT_CONCENTRATION =  {"10 BAU", "10 BAU", "10 BAU", "10 BAU", "100 BAU", "100 BAU", "100 BAU", "100 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "1,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU", "10,000 BAU"};
	private static String[] CAREFUL_CAT_DOSAGE = {"0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.03 ml", "0.05 ml", "0.07 ml", "0.1 ml", "0.15 ml", "0.2 ml", "0.25 ml", "0.3 ml", "0.35 ml", "0.4 ml", "0.45 ml", "0.5 ml", "0.03 ml", "0.05 ml", "0.07 ml", "0.1 ml", "0.15 ml", "0.2 ml", "0.25 ml", "0.3 ml"};
	private static String[] CAREFUL_CAT_COLOR = {"red", "red", "red", "red", "green", "green", "green", "green", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "blue", "black", "black", "black", "black", "black", "black", "black", "black"};
	
	

	private static String[] NORMAL_BEES_CONCENTRATION = {"1 ug/mg", "1 ug/mg", "1 ug/mg", "1 ug/mg", "10 ug/mg", "10 ug/mg", "10 ug/mg", "10 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg"};
	private static String[] NORMAL_BEES_DOSAGE = {"0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.02 ml", "0.05 ml", "0.07 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.6 ml", "0.8 ml", "0.1 ml"};
	private static String[] NORMAL_BEES_COLOR = {"green", "green", "green", "green", "blue", "blue", "blue", "blue", "black", "black", "black", "black", "black", "black", "black", "black", "black"};
	
	private static String[] CAREFUL_BEES_CONCENTRATION = {"1 ug/mg", "1 ug/mg", "1 ug/mg", "1 ug/mg", "10 ug/mg", "10 ug/mg", "10 ug/mg", "10 ug/mg", "10 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg", "100 ug/mg"};
	private static String[] CAREFUL_BEES_DOSAGE = {"0.05 ml", "0.1 ml", "0.2 ml", "0.4 ml", "0.05 ml", "0.1 ml", "0.2 ml", "0.3 ml", "0.4 ml", "0.02 ml", "0.05 ml", "0.07 ml", "0.1 ml", "0.15 ml", "0.2 ml", "0.3 ml", "0.4 ml", "0.5 ml", "0.6 ml", "0.7 ml",  "0.8 ml", "0.9 ml", "0.1 ml"};
	private static String[] CAREFUL_BEES_COLOR = {"green", "green", "green", "green", "blue", "blue", "blue", "blue", "blue", "black", "black", "black", "black", "black", "black", "black", "black", "black", "black", "black", "black", "black", "black"};




	@Autowired
	public InitDatabase(
			AddressDao addressDao,
			AllergenProtocolsDao allergenProtocolsDao,
			AllergensDao allergensDao,
			DiagnosisDao diagnosisDao,
			InjectionDao injectionDao,
			PatientDao patientDao,
			ProtocolDao protocolDao,
			SessionDao sessionDao,
			UserDao userDao,
			EntityFactory factory) {

		this.addressDao = addressDao;
		this.allergenProtocolsDao = allergenProtocolsDao;
		this.allergensDao = allergensDao;
		this.diagnosisDao = diagnosisDao;
		this.injectionDao = injectionDao;
		this.patientDao = patientDao;
		this.protocolDao = protocolDao;
		this.sessionDao = sessionDao;
		this.userDao = userDao;

		this.factory = factory;

	}


	@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {


		List<AllergensEntity> allergens = this.initAllergens();
		List<ProtocolEntity> protocols = this.initProtocols();

		List<UserEntity> users = this.initUsers();
		List<PatientEntity> patients = this.initPatients(users);
		
	        
			List<InjectionLocation> location1 = new ArrayList<InjectionLocation>();
			location1.add(InjectionLocation.Left);
			location1.add(InjectionLocation.LeftDown);
			location1.add(InjectionLocation.RightMiddle);

			List<InjectionLocation> location2 = new ArrayList<InjectionLocation>();
			location2.add(InjectionLocation.RightDown);
			location2.add(InjectionLocation.RightUp);
			


		DiagnosisEntity diagnosis = this.diagnosisDao.save(new DiagnosisEntity(null, 1, location1, patients.get(0), Set.of(allergens.get(1), allergens.get(2), allergens.get(8)), null));

		DiagnosisEntity diagnosis1 = this.diagnosisDao.save(new DiagnosisEntity(null, 2, location2, patients.get(0), Set.of(allergens.get(4), allergens.get(5), allergens.get(6)), null));

		
		AllergenProtocolsEntity allergenProtocolsEntity = this.allergenProtocolsDao.save(new AllergenProtocolsEntity(null, protocols.get(0), diagnosis, null, true));
		AllergenProtocolsEntity allergenProtocolsEntity1 = this.allergenProtocolsDao.save(new AllergenProtocolsEntity(null, protocols.get(1), diagnosis, null, false));

		
		AllergenProtocolsEntity allergenProtocolsEntity3 = this.allergenProtocolsDao.save(new AllergenProtocolsEntity(null, protocols.get(3), diagnosis1, null, false));

		
		for (int i = 0; i < 18; i++) {
			
			SessionEntity session = this.sessionDao.save(new SessionEntity(null, patients.get(0), users.get(1), new Date(), new QuestionEntity("good", null),  new QuestionEntity("good", null),  new QuestionEntity("no", null), null));

			this.injectionDao.save(new InjectionEntity(
					null,
					new Date(),	// years start from 1900, months between 0-11
					i+1,
					InjectionLocation.Left,
					"injection notes...",
					allergenProtocolsEntity,
					session,
					users.get(1),
					allergenProtocolsEntity.getProtocol().getConcentration().get(i),
					allergenProtocolsEntity.getProtocol().getDosage().get(i)));
			
		}



		SessionEntity session = this.sessionDao.save(new SessionEntity(null, patients.get(0), users.get(1), new Date(), new QuestionEntity("good", null),  new QuestionEntity("good", null),  new QuestionEntity("no", null), null));
		
		this.injectionDao.save(new InjectionEntity(
				null,
				new Date(),	// years start from 1900, months between 0-11
				1,
				InjectionLocation.RightUp,
				"injection notes...",
				allergenProtocolsEntity1,
				session,
				users.get(1),
				allergenProtocolsEntity.getProtocol().getConcentration().get(0),
				allergenProtocolsEntity.getProtocol().getDosage().get(0)));
		
		
//		this.injectionDao.save(new InjectionEntity(
//				null,
//				new Date(),	// years start from 1900, months between 0-11
//				1,
//				InjectionLocation.Left,
//				null,
//				allergenProtocolsEntity,
//				session,
//				users.get(1),
//				allergenProtocolsEntity.getProtocol().getConcentration_au().get(0),
//				allergenProtocolsEntity.getProtocol().getDosage_ml().get(0)));


		
		
//		System.err.println(this.diagnosisDao.findAllByPatient( patients.get(0), PageRequest.of(0, 100)));

		
//				this.injectionDao.save(new InjectionEntity(
//						null,
//						new Date(120, 10, 13),	// years start from 1900, months between 0-11
//						1,
//						InjectionLocation.Left,
//						null,
//						allergenProtocolsEntity,
//						session,
//						users.get(1),
//						allergenProtocolsEntity.getProtocol().getConcentration_au().get(0),
//						allergenProtocolsEntity.getProtocol().getDosage_ml().get(0)));
//				
//			
//		
//				this.injectionDao.save(new InjectionEntity(
//						null,
//						new Date(120, 10, 14),	// years start from 1900, months between 0-11
//						2,
//						InjectionLocation.Left,
//						null,
//						allergenProtocolsEntity,
//						session,
//						users.get(1),
//						allergenProtocolsEntity.getProtocol().getConcentration_au().get(1),
//						allergenProtocolsEntity.getProtocol().getDosage_ml().get(1)));


				
				
				
				
				
				

		//		this.injectionDao.save(new InjectionEntity(
		//				null,
		//				new Date(120, 5, 14),	// years start from 1900, months between 0-11
		//				24,
		//				InjectionLocation.Left,
		//				null,
		//				allergenProtocolsEntity,
		//				session,
		//				users.get(1),
		//				allergenProtocolsEntity.getProtocol().getConcentration_au().get(23),
		//				allergenProtocolsEntity.getProtocol().getDosage_ml().get(23)));
		//		
				
				
				

	}





	private List<AllergensEntity> initAllergens(){

		List<AllergensEntity> allergens = new ArrayList<AllergensEntity>();


		for(String allergen : ALLERGEENS) {

			allergens.add(this.allergensDao.save(new AllergensEntity(null, allergen, null)));
		}

		return allergens;
	}


	private List<ProtocolEntity> initProtocols(){

		List<ProtocolEntity> protocols = new ArrayList<ProtocolEntity>();

		protocols.add(this.protocolDao.save(this.initProtocol("Normal Dust", NORMAL_DUST_CONCENTRATION, NORMAL_DUST_DOSAGE, NORMAL_DUST_COLOR)));
		protocols.add(this.protocolDao.save(this.initProtocol("Careful Dust", CAREFUL_DUST_CONCENTRATION, CAREFUL_DUST_DOSAGE, CAREFUL_DUST_COLOR)));


		protocols.add(this.protocolDao.save(this.initProtocol("Normal Inhaled", NORMAL_INHALED_CONCENTRATION, NORMAL_INHALED_DOSAGE, NORMAL_INHALED_COLOR)));
		protocols.add(this.protocolDao.save(this.initProtocol("Careful Inhaled", CAREFUL_INHALED_CONCENTRATION, CAREFUL_INHALED_DOSAGE, CAREFUL_INHALED_COLOR)));


		protocols.add(this.protocolDao.save(this.initProtocol("Normal Cat", NORMAL_CAT_CONCENTRATION, NORMAL_CAT_DOSAGE, NORMAL_CAT_COLOR)));
		protocols.add(this.protocolDao.save(this.initProtocol("Careful Cat", CAREFUL_CAT_CONCENTRATION, CAREFUL_CAT_DOSAGE, CAREFUL_CAT_COLOR)));

		protocols.add(this.protocolDao.save(this.initProtocol("Normal Bee", NORMAL_BEES_CONCENTRATION, NORMAL_BEES_DOSAGE, NORMAL_BEES_COLOR)));
		protocols.add(this.protocolDao.save(this.initProtocol("Careful Bee", CAREFUL_BEES_CONCENTRATION, CAREFUL_BEES_DOSAGE, CAREFUL_BEES_COLOR)));


		return protocols;

	}




	private ProtocolEntity initProtocol(String name, String[] CONCENTRATION, String[] DOSAGE, String[] COLORS) {


		List<String> concentration = new ArrayList<String>();
		List<String> dosage = new ArrayList<String>();
		List<String> colors = new ArrayList<String>();

		for(int i=0 ; i < CONCENTRATION.length ; i++) {

			concentration.add(i, CONCENTRATION[i]);
			dosage.add(i, DOSAGE[i]);
			colors.add(i, COLORS[i]);

		}

		return new ProtocolEntity(null, name, CONCENTRATION.length, concentration, dosage, colors, null);
	}




	private List<PatientEntity> initPatients(List<UserEntity> users){

		List<PatientEntity> patients = new ArrayList<PatientEntity>();

		PatientEntity patient1 = new PatientEntity(
				"123456789",
				"wowo",
				"roro",
				"gogo",
				LocalDate.of(1994, 10, 20),
				Gender.MALE,
				"+9720502862222",
				"+9720502862222",
				this.addressDao.save(new AddressEntity(null, "Israel", "Tel-Aviv", "allemby", 42, 32184)),
				true,
				Hmo.Clalit,
				new Date(),
				new Date(),
				users.get(1),
				users.get(0),
				VaccineStatus.Increase,
				new QuestionEntity("false", ""),
				"Rhinitis",
				"yes",
				"no notes",
				null,
				null);

		patients.add(this.patientDao.save(patient1));

		PatientEntity patient2 = new PatientEntity(
				"23498721",
				"lala",
				"tata",
				"dada",
				LocalDate.of(1945, 11, 01),
				Gender.FEMALE,
				"+9720502862222",
				"+9720502862222",
				this.addressDao.save(new AddressEntity(null, "Israel", "Tel-Aviv", "allemby", 22, 8236)),
				true,
				Hmo.Macabi,
				new Date(),
				new Date(),
				users.get(0),
				users.get(1),
				VaccineStatus.MaintenanceDose,
				new QuestionEntity("true", "for moxypen"),
				"Asthma",
				"yes",
				"no notes",
				null,
				null);

		patients.add(this.patientDao.save(patient2));
		
		
		
		
		
		
		PatientEntity patient3 = new PatientEntity(
				"534982721",
				"unv",
				"tata",
				"dada",
				LocalDate.of(1945, 11, 01),
				Gender.FEMALE,
				"+9720502862222",
				"+9720502862222",
				this.addressDao.save(new AddressEntity(null, "Israel", "Tel-Aviv", "allemby", 22, 8236)),
				true,
				Hmo.Macabi,
				new Date(),
				new Date(),
				users.get(0),
				null,
				VaccineStatus.MaintenanceDose,
				new QuestionEntity("true", "for moxypen"),
				"Asthma",
				"yes",
				"no notes",
				null,
				null);

		patients.add(this.patientDao.save(patient3));
		
		
		
		PatientEntity patient4 = new PatientEntity(
				"534587121",
				"unv",
				"tata",
				"dada",
				LocalDate.of(1945, 11, 01),
				Gender.FEMALE,
				"+9720502862222",
				"+9720502862222",
				this.addressDao.save(new AddressEntity(null, "Israel", "Tel-Aviv", "allemby", 22, 8236)),
				true,
				Hmo.Macabi,
				new Date(),
				new Date(),
				users.get(0),
				null,
				VaccineStatus.MaintenanceDose,
				new QuestionEntity("true", "for moxypen"),
				"Rhinitis",
				"yes",
				"no notes",
				null,
				null);

		patients.add(this.patientDao.save(patient4));
		
		
		
		


		return patients;
	}





	private List<UserEntity> initUsers(){


		List<UserEntity> users = new ArrayList<UserEntity>();

		UserEntity user1 = this.factory.createNewUser(
				"20857162",
				"12345",
				"momo",
				"fofo",
				LocalDate.of(2020, 04, 14),
				Gender.MALE,
				"+9720502862222",
				this.addressDao.save(new AddressEntity(null, "Israel", "Tel-Aviv", "Hamoraim", 13, 6596509)),
				true,
				new Date(),
				"ali-stam@gmail.com",
				"ali123",
				UserRole.MANAGER,
				null,
				null,
				null,
				null
				);

		users.add(userDao.save(user1));

		UserEntity user2 = this.factory.createNewUser(
				"20857161",
				"56789",
				"lolo",
				"koko",
				LocalDate.of(1995, 01, 20),
				Gender.FEMALE,
				"+9720502861111",
				this.addressDao.save(new AddressEntity(null, "Israel", "Haifa", "Hamoraim", 13, 6596509)),
				true,
				new Date(),
				"lolo-stam@gmail.com",
				"lolo123",
				UserRole.Nurse,
				null,
				null,
				null,
				null
				);

		users.add(userDao.save(user2));


		UserEntity user3 = this.factory.createNewUser(
				"123456789",
				"111111",
				"admin",
				"admin",
				LocalDate.of(1995, 01, 20),
				Gender.MALE,
				"+9720502861111",
				this.addressDao.save(new AddressEntity(null, "Israel", "Haifa", "Hamoraim", 13, 6596509)),
				true,
				new Date(),
				"admin@admin.com",
				"admin123",
				UserRole.ADMIN,
				null,
				null,
				null,
				null
				);

		users.add(userDao.save(user3));

		return users;

	}























}
