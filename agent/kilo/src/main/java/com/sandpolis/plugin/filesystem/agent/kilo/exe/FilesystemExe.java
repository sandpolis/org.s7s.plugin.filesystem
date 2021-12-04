//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.plugin.filesystem.agent.kilo.exe;

import static com.sandpolis.core.instance.stream.StreamStore.StreamStore;

import java.nio.file.Paths;

import com.google.common.io.MoreFiles;
import com.google.common.io.RecursiveDeleteOption;
import com.sandpolis.core.foundation.S7SSystem;
import com.sandpolis.core.instance.exelet.Exelet;
import com.sandpolis.core.instance.exelet.ExeletContext;
import com.sandpolis.core.instance.stream.OutboundStreamAdapter;
import com.sandpolis.plugin.filesystem.DirectoryStreamSource;
import com.sandpolis.plugin.filesystem.Messages.RQ_DirectoryStream;
import com.sandpolis.plugin.filesystem.Messages.RS_DirectoryStream;
import com.sandpolis.plugin.filesystem.Messages.EV_DirectoryStream;
import com.sandpolis.plugin.filesystem.Messages.RQ_DeleteFile;
import com.sandpolis.plugin.filesystem.Messages.RS_DeleteFile;

public final class FilesystemExe extends Exelet {

	@Handler(auth = true)
	public static RS_DirectoryStream rq_directory_stream(ExeletContext context, RQ_DirectoryStream rq)
			throws Exception {
		String path;
		switch (S7SSystem.OS_TYPE) {
		case WINDOWS:
			path = rq.getPath().startsWith("/") ? rq.getPath().substring(1) : rq.getPath();
			if (path.equals("C:"))
				path = "/";
			break;
		default:
			path = rq.getPath();
		}

		var source = new DirectoryStreamSource(rq);
		var outbound = new OutboundStreamAdapter<EV_DirectoryStream>(rq.getStreamId(), context.connector,
				context.request.getFrom());
		StreamStore.add(source, outbound);

		context.defer(() -> {
			source.start();
		});

		return RS_DirectoryStream.DIRECTORY_STREAM_OK;
	}

	@Handler(auth = true)
	public static RS_DeleteFile rq_delete_file(RQ_DeleteFile rq) throws Exception {
		switch (S7SSystem.OS_TYPE) {
		case WINDOWS:
			for (var path : rq.getTargetList()) {
				MoreFiles.deleteRecursively(Paths.get(path.startsWith("/") ? path.substring(1) : path),
						RecursiveDeleteOption.ALLOW_INSECURE);
			}
			break;
		default:
			for (var path : rq.getTargetList()) {
				MoreFiles.deleteRecursively(Paths.get(path), RecursiveDeleteOption.ALLOW_INSECURE);
			}
		}

		return RS_DeleteFile.DELETE_FILE_OK;
	}

	private FilesystemExe() {
	}
}
