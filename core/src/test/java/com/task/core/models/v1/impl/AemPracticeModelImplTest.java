package com.task.core.models.v1.impl;

import com.day.cq.wcm.api.Page;
import com.task.core.models.AemPracticeModel;
import com.task.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class AemPracticeModelImplTest {
	private final AemContext context = AppAemContext.newAemContext();
	private Resource componentResource;

	@BeforeEach
	void setup() {
		// prepare a page with a test resource
		Page page = context.create().page("/content/mypage");
		componentResource = context.create().resource(page, "aempractice",
				"sling:resourceType", "my-project/components/aempractice",
				"imagePath", "/content/dam/my-project/asset.jpg",
				"title", "Sample Title",
				"backgroundImage", false
		);

		context.addModelsForPackage("com.task.core.models");
	}

	@Test
	void testModel() {
		AemPracticeModel model = componentResource.adaptTo(AemPracticeModel.class);
		assertNotNull(model);
		assertEquals("/content/dam/my-project/asset.jpg", model.getImagePath());
		assertEquals("Sample Title", model.getTitle());
		assertEquals(false, model.isBackgroundImage());
		assertTrue(model.hasContent());
	}

	@Test
	void testGetExportedType() {
		AemPracticeModelImpl model = new AemPracticeModelImpl();
		assertEquals(AemPracticeModelImpl.RESOURCE_TYPE, model.getExportedType());
	}

	@Test
	void testHasContent() {
		AemPracticeModel model = new AemPracticeModelImpl();
		assertFalse(model.hasContent());
	}
}