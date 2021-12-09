//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
module com.sandpolis.plugin.filesystem {
	exports com.sandpolis.plugin.filesystem.cmd;
	exports com.sandpolis.plugin.filesystem;

	requires com.google.common;
	requires com.google.protobuf;
	requires com.sandpolis.core.foundation;
	requires com.sandpolis.core.instance;
	requires com.sandpolis.core.integration.fuse;
	requires java.desktop;
	requires org.slf4j;
	requires jdk.incubator.foreign;
}
