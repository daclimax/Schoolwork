package impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import service.ILogicController;
import service.IUserInterface;
import bean.Computer;
import bean.NetworkInterfaceCard;
import bean.OperatingSystem;
import bean.SearchResult;
import bean.Software;
import bean.SuperBean;
import constants.Attributes;

/**
 * implementation for the console output
 * <p>
 * </p>
 * 
 * 
 * <br>
 * &nbsp;
 * 
 * @author ETHALON: tmy
 * 
 */

public class ConsoleImpl implements IUserInterface {

	private final ILogicController logic;

	public ConsoleImpl() {
		this.logic = new LogicImpl();
	}

	public void deleteComponent() {
		Short deleteSelection;
		do {
			System.out.println("\nWas für eine Komponente soll gelöscht werden?");
			System.out.println("(1) Computer");
			System.out.println("(2) Netzwerkkarte");
			System.out.println("(3) Betriebssystem");
			System.out.println("(4) Software");
			System.out.println("(0) Zurück zum Hauptmenü");

			System.out.println("\nAuswahl: ");

			final Scanner deleteInput = new Scanner(System.in);
			deleteSelection = deleteInput.nextShort();

			final List<String> messages;
			switch (deleteSelection) {

			case 1: // delete computer
				// get the id to delete the computer with given id
				final Computer computer = new Computer();
				System.out.println("\n\nGeben Sie die ID des zu löschenden Computers ein: ");
				final Scanner comId = new Scanner(System.in);
				computer.setId(comId.nextInt());

				messages = this.logic.deleteComponent(computer);
				for (final String msg : messages) {
					System.out.println(msg);
				}
				break;

			case 2: // delete networkInterfaceCard
				// get the id to delete the networkInterfaceCard with given id
				final NetworkInterfaceCard nic = new NetworkInterfaceCard();
				System.out.println("\n\nGeben Sie die ID des zu löschenden Computers ein: ");
				final Scanner nicId = new Scanner(System.in);
				nic.setId(nicId.nextInt());

				messages = this.logic.deleteComponent(nic);
				for (final String msg : messages) {
					System.out.println(msg);
				}
				break;

			case 3: // delete operatingSystem
				// get the id to delete the operatingSystem with given id
				final OperatingSystem os = new OperatingSystem();
				System.out.println("\n\nGeben Sie die ID des zu löschenden Computers ein: ");
				final Scanner osId = new Scanner(System.in);
				os.setId(osId.nextInt());

				messages = this.logic.deleteComponent(os);
				for (final String msg : messages) {
					System.out.println(msg);
				}
				break;

			case 4: // delete software
				// get the id to delete the operatingSystem with given id
				final Software software = new Software();
				System.out.println("\n\nGeben Sie die ID des zu löschenden Computers ein: ");
				final Scanner softwareId = new Scanner(System.in);
				software.setId(softwareId.nextInt());

				messages = this.logic.deleteComponent(software);
				for (final String msg : messages) {
					System.out.println(msg);
				}
				break;

			case 0: // exit to main menu
				break;

			default:
				System.err.println("\nFalsche Eingabe!");
			}
		} while (deleteSelection != 0);

	}

	public void insertNewComponent() {

	}

	public void search() {
		// TODO Auto-generated method stub
		Short searchSelection;
		do {
			System.out.println("\nWonach wollen Sie suchen?");
			System.out.println("(1) Zusammenhängende Daten");
			System.out.println("(2) Einzelne Komponente");
			System.out.println("(0) Zurück zum Hauptmenü");

			System.out.println("\nAuswahl: ");

			final Scanner searchInput = new Scanner(System.in);
			searchSelection = searchInput.nextShort();
			final Map<String, String> searchAttributes = new HashMap<String, String>();
			switch (searchSelection) {

			case 1: // coherent search
				String attribute;
				System.out.println("\n\nGeben Sie bei den Attributen Daten ein, nach denen gesucht werden soll");
				System.out.println("(Enter drücken, wenn das Attribut bei der Suche nicht berücksichtigt werden soll)");

				// iterate over the field-description maps to put the searchAttributes into a new map
				// computer search attributes
				for (final String field : Attributes.COMPUTER_FIELD_DESCRIPTION_MAP.keySet()) {
					System.out.println("\n" + Attributes.COMPUTER_FIELD_DESCRIPTION_MAP.get(field));
					final Scanner searchAttributeInput = new Scanner(System.in);
					attribute = searchAttributeInput.nextLine();
					if (!attribute.equals("")) {
						searchAttributes.put(field, attribute);
					}
				}
				// nic search attributes
				for (final String field : Attributes.NIC_FIELD_DESCRIPTION_MAP.keySet()) {
					System.out.println("\n" + Attributes.NIC_FIELD_DESCRIPTION_MAP.get(field));
					final Scanner searchAttributeInput = new Scanner(System.in);
					attribute = searchAttributeInput.nextLine();
					if (!attribute.equals("")) {
						searchAttributes.put(field, attribute);
					}
				}
				// os search attributes
				for (final String field : Attributes.OPERATING_SYSTEM_FIELD_DESCRIPTION_MAP.keySet()) {
					System.out.println("\n" + Attributes.OPERATING_SYSTEM_FIELD_DESCRIPTION_MAP.get(field));
					final Scanner searchAttributeInput = new Scanner(System.in);
					attribute = searchAttributeInput.nextLine();
					if (!attribute.equals("")) {
						searchAttributes.put(field, attribute);
					}
				}
				// software search attributes
				for (final String field : Attributes.SOFTWARE_FIELD_DESCRIPTION_MAP.keySet()) {
					System.out.println("\n" + Attributes.SOFTWARE_FIELD_DESCRIPTION_MAP.get(field));
					final Scanner searchAttributeInput = new Scanner(System.in);
					attribute = searchAttributeInput.nextLine();
					if (!attribute.equals("")) {
						searchAttributes.put(field, attribute);
					}
				}

				final List<SearchResult> results = this.logic.searchDataByAttributes(searchAttributes);

				// output the search results
				System.out.println("\nErgebnisse:");
				for (final SearchResult searchResult : results) {
					System.out.println(searchResult.toString());
				}
				break;

			case 2: // single component search - let the user chose the component, therefore there's a new switch-case.
				Short searchComponent;
				do {
					final Map<String, String> componentSearchAttributes = new HashMap<String, String>();
					System.out.println("\nNach welcher Komponente wollen Sie suchen?");
					System.out.println("(1) Computer");
					System.out.println("(2) Netwerkkarte");
					System.out.println("(3) Betriebssystem");
					System.out.println("(4) Software");
					System.out.println("(0) Zurück zum vorherigen Menü");

					System.out.println("\nAuswahl: ");

					final Scanner componentInput = new Scanner(System.in);
					searchComponent = componentInput.nextShort();

					switch (searchComponent) {

					case 1: // Computer search
						for (final String field : Attributes.COMPUTER_FIELD_DESCRIPTION_MAP.keySet()) {
							System.out.println("\n" + Attributes.COMPUTER_FIELD_DESCRIPTION_MAP.get(field));
							final Scanner searchAttributeInput = new Scanner(System.in);
							attribute = searchAttributeInput.nextLine();
							if (!attribute.equals("")) {
								componentSearchAttributes.put(field, attribute);
							}
						}
						final List<SuperBean> computers = this.logic.searchComponentsByAttributes(
								componentSearchAttributes, "computer");
						for (final SuperBean computer : computers) {
							System.out.println("test");
							if (computer instanceof Computer) {
								System.out.println("test2");
								System.out.println(computer.toString());
							}
						}

						break;

					case 2: // NIC search
						for (final String field : Attributes.NIC_FIELD_DESCRIPTION_MAP.keySet()) {
							System.out.println("\n" + Attributes.NIC_FIELD_DESCRIPTION_MAP.get(field));
							final Scanner searchAttributeInput = new Scanner(System.in);
							attribute = searchAttributeInput.nextLine();
							if (!attribute.equals("")) {
								componentSearchAttributes.put(field, attribute);
							}
						}
						final List<SuperBean> NICs = this.logic.searchComponentsByAttributes(componentSearchAttributes,
								"nic");
						for (final SuperBean nic : NICs) {
							if (nic instanceof NetworkInterfaceCard) {
								nic.toString();
							}
						}

						break;

					case 3: // OS search
						for (final String field : Attributes.OPERATING_SYSTEM_FIELD_DESCRIPTION_MAP.keySet()) {
							System.out.println("\n" + Attributes.OPERATING_SYSTEM_FIELD_DESCRIPTION_MAP.get(field));
							final Scanner searchAttributeInput = new Scanner(System.in);
							attribute = searchAttributeInput.nextLine();
							if (!attribute.equals("")) {
								componentSearchAttributes.put(field, attribute);
							}
						}
						final List<SuperBean> operatingSystems = this.logic.searchComponentsByAttributes(
								componentSearchAttributes, "operatingSystem");
						for (final SuperBean os : operatingSystems) {
							if (os instanceof OperatingSystem) {
								os.toString();
							}
						}

						break;

					case 4: // software search
						for (final String field : Attributes.SOFTWARE_FIELD_DESCRIPTION_MAP.keySet()) {
							System.out.println("\n" + Attributes.SOFTWARE_FIELD_DESCRIPTION_MAP.get(field));
							final Scanner searchAttributeInput = new Scanner(System.in);
							attribute = searchAttributeInput.nextLine();
							if (!attribute.equals("")) {
								componentSearchAttributes.put(field, attribute);
							}
						}
						final List<SuperBean> softwares = this.logic.searchComponentsByAttributes(
								componentSearchAttributes, "software");
						for (final SuperBean software : softwares) {
							software.toString();
						}

						break;

					case 0: // exit to previous search menu
						break;
					default:
						System.err.println("\nFalsche Eingabe!");
					}
				} while (searchComponent != 0);

			case 0: // exit to main menu
				break;
			default:
				System.err.println("\nFalsche Eingabe!");
			}
		} while (searchSelection != 0);
	}

	/** {@inheritDoc} */
	public void updateComponent() {
		// TODO Auto-generated method stub

	}

}
