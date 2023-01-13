package com.task.core.models.v1.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.task.core.models.AemPracticeModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

import static com.adobe.cq.export.json.ExporterConstants.SLING_MODEL_EXPORTER_NAME;
import static com.adobe.cq.export.json.ExporterConstants.SLING_MODEL_EXTENSION;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
	defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
	resourceType = AemPracticeModelImpl.RESOURCE_TYPE,
	adapters = {AemPracticeModel.class, ComponentExporter.class})
@Exporter(name = SLING_MODEL_EXPORTER_NAME, extensions = SLING_MODEL_EXTENSION)
public class AemPracticeModelImpl implements AemPracticeModel, ComponentExporter {

	public static final String RESOURCE_TYPE = "my-project/components/aem-practice";

	@ValueMapValue
	private String imagePath;

	@ValueMapValue
	private String title;

	@ValueMapValue
	private boolean backgroundImage;

	@ChildResource
	private List<Resource> navigationItems;

	@Override
	public String getExportedType() {
		return RESOURCE_TYPE;
	}

	@Override
	public boolean hasContent() {
		return isNotEmpty(imagePath) || isNotEmpty(title) || (navigationItems != null && navigationItems.size() > 0);
	}

	@Override
	public String getImagePath() {
		return imagePath;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public boolean isBackgroundImage() {
		return backgroundImage;
	}

	@Override
	public List<Resource> getNavigationItems() {
		return navigationItems;
	}
}
