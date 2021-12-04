//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.plugin.filesystem.agent.kilo;

import com.sandpolis.core.instance.plugin.SandpolisPlugin;
import com.sandpolis.core.instance.exelet.Exelet;
import com.sandpolis.core.instance.plugin.ExeletProvider;
import com.sandpolis.plugin.filesystem.agent.kilo.exe.FilesystemExe;

public final class FilesystemPlugin extends SandpolisPlugin implements ExeletProvider {

	@Override
	@SuppressWarnings("unchecked")
	public Class<? extends Exelet>[] getExelets() {
		return new Class[] { FilesystemExe.class };
	}
}
