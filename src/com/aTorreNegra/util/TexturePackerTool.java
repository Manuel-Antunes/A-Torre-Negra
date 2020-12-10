package com.aTorreNegra.util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerTool {
	public static void main(String[] args) {
		TexturePacker.process("div/unpacked/", "div/packed/", "textures");
	}
}
