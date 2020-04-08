package it.interlogic.vimp.web.page;

import it.interlogic.vimp.web.AbstractHandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InformazioneAbstractHandler extends AbstractHandler
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.interlogic.vimp.web.AbstractHandler#getPageName()
	 */
	@Override
	public String getPageName()
	{
		return null;
	}

	/**
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/uploadImage")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
	{

		if (file.isEmpty())
		{
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		try
		{

			// byte[] bytes = file.getBytes();
			redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/uploadStatus")
	public String uploadStatus()
	{
		return "uploadStatus";
	}

}
