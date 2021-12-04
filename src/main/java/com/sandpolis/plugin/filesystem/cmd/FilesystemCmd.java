//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.plugin.filesystem.cmd;

import com.sandpolis.core.instance.cmdlet.Cmdlet;

/**
 * Contains filesystem commands.
 *
 * @author cilki
 * @since 5.0.2
 */
public final class FilesystemCmd extends Cmdlet<FilesystemCmd> {

	/**
	 * Prepare for an asynchronous command.
	 *
	 * @return A configurable object from which all asynchronous (nonstatic)
	 *         commands in {@link FilesystemCmd} can be invoked
	 */
	public static FilesystemCmd async() {
		return new FilesystemCmd();
	}

	private FilesystemCmd() {
	}
}
