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
import static it.sauronsoftware.ftp4j.FTPFile.TYPE_DIRECTORY;
import static it.sauronsoftware.ftp4j.FTPFile.TYPE_FILE;

/**
 *
 * @author Benjamin Asbach (asbachb), 2015
 */
public class EPLFListParserTest {

	EPLFListParser parser = new EPLFListParser();

	String line1 = "+i8388621.29609,m824255902,/,\tdev";
	String line2 = "+i8388621.44468,m839956783,r,s10376,\tRFCEPLF";
	String[] lines = {line1, line2};

	@Test
	public void nameIsParsed() throws Exception {
		FTPFile[] ftpFiles = parser.parse(new String[]{line1});
		assertEquals("dev", ftpFiles[0].getName());
	}

	@Test
	public void typeDirectoryIsParsed() throws Exception {
		FTPFile[] ftpFiles = parser.parse(new String[]{line1});
		assertEquals(TYPE_DIRECTORY, ftpFiles[0].getType());
	}

	@Test
	public void typeFileIsParsed() throws Exception {
		FTPFile[] ftpFiles = parser.parse(new String[]{line2});
		assertEquals(TYPE_FILE, ftpFiles[0].getType());
	}

	@Test
	public void sizeIsParsed() throws Exception {
		FTPFile[] ftpFiles = parser.parse(new String[]{line2});
		assertEquals(10376, ftpFiles[0].getSize());
	}

	@Test
	public void multipleLinesAreParsed() throws Exception {
		FTPFile[] ftpFiles = parser.parse(lines);
		assertEquals(2, ftpFiles.length);
	}
}
