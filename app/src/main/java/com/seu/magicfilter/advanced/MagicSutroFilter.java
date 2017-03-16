package com.seu.magicfilter.advanced;

import android.opengl.GLES20;

import com.example.yihuii.yihuii.R;

import com.seu.magicfilter.base.gpuimage.GPUImageFilter;
import com.seu.magicfilter.utils.MagicFilterFactory;
import com.seu.magicfilter.utils.OpenGlUtils;

public class MagicSutroFilter extends GPUImageFilter{
	private int[] inputTextureHandles = {-1,-1,-1,-1,-1};
	private int[] inputTextureUniformLocations = {-1,-1,-1,-1,-1};
    private int mGLStrengthLocation;

	public MagicSutroFilter(){
		super(NO_FILTER_VERTEX_SHADER, OpenGlUtils.readShaderFromRawResource(R.raw.sutro));
	}

	@Override
	protected void onDestroy() {
        super.onDestroy();
        GLES20.glDeleteTextures(inputTextureHandles.length, inputTextureHandles, 0);
        for (int i = 0; i < inputTextureHandles.length; i++) {
			inputTextureHandles[i] = -1;
		}
    }
	
	protected void onDrawArraysAfter(){
		for(int i = 0; i < inputTextureHandles.length && inputTextureHandles[i] != OpenGlUtils.NO_TEXTURE; i++){
			GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + (i+3));
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
			GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		}
	}

	@Override
	protected void onDrawArraysPre(){
		for(int i = 0; i < inputTextureHandles.length && inputTextureHandles[i] != OpenGlUtils.NO_TEXTURE; i++){
			GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + (i+3) );
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, inputTextureHandles[i]);
			GLES20.glUniform1i(inputTextureUniformLocations[i], (i+3));
		}
	}

	@Override
	protected void onInit() {
		super.onInit();
		for(int i = 0; i < inputTextureUniformLocations.length; i++) {
			inputTextureUniformLocations[i] = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture" + (2 + i));
		}
		mGLStrengthLocation = GLES20.glGetUniformLocation(mGLProgId, "strength");
	}

	@Override
	protected void onInitialized() {
		super.onInitialized();
		setFloat(mGLStrengthLocation, 1.0f);
	    runOnDraw(new Runnable(){
		    public void run(){
				inputTextureHandles[0] = OpenGlUtils.loadTexture(MagicFilterFactory.getCurrentContext(), "filter/vignette_map.png");
				inputTextureHandles[1] = OpenGlUtils.loadTexture(MagicFilterFactory.getCurrentContext(), "filter/sutrometal.png");
				inputTextureHandles[2] = OpenGlUtils.loadTexture(MagicFilterFactory.getCurrentContext(), "filter/softlight.png");
				inputTextureHandles[3] = OpenGlUtils.loadTexture(MagicFilterFactory.getCurrentContext(), "filter/sutroedgeburn.png");
				inputTextureHandles[4] = OpenGlUtils.loadTexture(MagicFilterFactory.getCurrentContext(), "filter/sutrocurves.png");
		    }
	    });
	}
}
