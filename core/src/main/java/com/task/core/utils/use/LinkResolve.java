package com.task.core.utils.use;

import com.adobe.cq.sightly.WCMUsePojo;
import com.task.core.utils.JCRUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LinkResolve extends WCMUsePojo {

	private static final String HREF_TEXT = "href";
	private static final String TARGET_TEXT = "target";
	private static final String HTML_EXTENSION = ".html";

	@Getter
	private String href = "";

	@Getter
	private String target = "";

	@Override
	public void activate() {
		href = get(HREF_TEXT, String.class);
		target = get(TARGET_TEXT, String.class);

		if (href != null && !href.isEmpty()) {
			boolean isExternalLink = JCRUtil.isExternalLink(href);
			boolean isInternalAsset = JCRUtil.isInternalAsset(href);

			if (!isExternalLink && !isInternalAsset) {
				if (getResourceResolver() != null) {
					href = getResourceResolver().map(href);
				}

				href = (href.endsWith(HTML_EXTENSION) || href.contains("?")) ?
					href : (href + HTML_EXTENSION);
			}
		}

		if (target == null || target.isEmpty())
			target = "_self";
	}
}
