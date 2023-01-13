package com.task.core.models;

import org.apache.sling.api.resource.Resource;

import java.util.List;

public interface AemPracticeModel extends HasContent {
	String getImagePath();
	String getTitle();
	boolean isBackgroundImage();
	List<Resource> getNavigationItems();
}
