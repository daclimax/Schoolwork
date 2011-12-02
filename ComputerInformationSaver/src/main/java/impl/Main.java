package impl;

import java.util.Scanner;

import service.IUserInterface;

/**
 * TODO tmy (02.12.2011): Insert javadoc for class impl.Main.java.
 * <p>
 * </p>
 * 
 * 
 * <br>
 * &nbsp;<br>
 * Licensed Materials - Property of <b>ETHALON GmbH</b> <br>
 * (c) Copyright ETHALON GmbH 2011. All rights reserved. <br>
 * Class Main.java created on 02.12.2011 15:19:58
 * 
 * <br>
 * &nbsp;<br>
 * <b>History:</b><br>
 * 
 * <br>
 * &nbsp;
 * 
 * @author ETHALON: tmy
 * 
 */

public class Main {

	public static void main(final String[] args) {

		final IUserInterface consoleInterface = new ConsoleImpl();
		Short selection;
		do {
			System.out.println("\nComputer Information Saver");
			System.out.println("====================================");
			System.out.println("\n(1) Suche");
			System.out.println("(2) Bearbeiten");
			System.out.println("(3) Einfügen");
			System.out.println("(4) Löschen");
			System.out.println("(0) Programm beenden");

			System.out.println("\nAuswahl: ");

			final Scanner input = new Scanner(System.in);
			selection = input.nextShort();

			switch (selection) {

			case 1: // search
				consoleInterface.search();
				break;

			case 2: // update
				consoleInterface.updateComponent();
				break;

			case 3: // insert
				consoleInterface.insertNewComponent();
				break;

			case 4: // delete
				consoleInterface.deleteComponent();
				break;

			case 0: // exit
				System.out.println("\nProgramm wurde beendet.");
				break;

			default:
				System.err.println("\nFalsche Eingabe!");
			}

		} while (selection != 0);
	}
}
