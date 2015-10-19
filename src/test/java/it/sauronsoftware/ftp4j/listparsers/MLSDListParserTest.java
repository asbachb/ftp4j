/*
 * ftp4j - A pure Java FTP client library
 * 
 * Copyright (C) 2015 Benjamin Asbach
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version
 * 2.1, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License 2.1 for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License version 2.1 along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package it.sauronsoftware.ftp4j.listparsers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import it.sauronsoftware.ftp4j.FTPFile;
import static it.sauronsoftware.ftp4j.FTPFile.TYPE_FILE;

/**
 *
 * @author Benjamin Asbach (asbachb), 2015
 */
public class MLSDListParserTest {

	MLSDListParser parser = new MLSDListParser();

	String line1 = "size=4161;lang=en-US;modify=19970214165800;create=19961001124534;type=file;x.myfact=foo,bar; cap;mux.tar.gz";
	String line2 = "type=file;x.myfact=foo,bar; mux.tar.gz";

	@Test
	public void typeFileIsParsed() throws Exception {
		FTPFile[] ftpFiles = parser.parse(new String[]{line1});
		assertEquals(TYPE_FILE, ftpFiles[0].getType());
	}

	@Test
	public void sizeIsParsed() throws Exception {
		FTPFile[] ftpFiles = parser.parse(new String[]{line1});
		assertEquals(4161, ftpFiles[0].getSize());
	}

	@Test
	public void filenameWithColonIsParsed() throws Exception {
		FTPFile[] ftpFiles = parser.parse(new String[]{line1});
		assertEquals("cap;mux.tar.gz", ftpFiles[0].getName());
	}

	@Test
	public void filenameIsParsed() throws Exception {
		FTPFile[] ftpFiles = parser.parse(new String[]{line2});
		assertEquals("mux.tar.gz", ftpFiles[0].getName());
	}
}
