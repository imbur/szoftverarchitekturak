package hu.bme.mit.mercury.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * This class find the validation markers and return a multiple status containing all the messages
 * 
 * Based on the implementation by Jacques Lescot and David Sciamma
 */
public class MarkerUtil2
{

    /**
     * Returns the markers associated with the given EObject
     * 
     * @param object the EObject
     * @return the list of markers
     */
	public static IMarker[] getMarkers(EObject object)
    {
        List<IMarker> markerList = new ArrayList<>();

        String objectURI = EcoreUtil.getURI(object).toString();
        ResourceSet set = object.eResource().getResourceSet();

        if (set != null)
        {
            List<Object> copiedResources = new ArrayList<>(set.getResources());
            Iterator<Object> it = copiedResources.iterator(); 
            while (it.hasNext())
            {
                Resource res = (Resource) it.next();
                IFile file = getFile(res);
                if (file != null)
                {
                    try
                    {
                        IMarker[] markers = file.findMarkers(EValidator.MARKER, true, IResource.DEPTH_ZERO);
                        for (int i = 0; i < markers.length; i++)
                        {
                            IMarker marker = markers[i];

                            String uriAttribute = (String) marker.getAttribute(EValidator.URI_ATTRIBUTE);
                            // This is a marker for this object
                            if (uriAttribute != null && uriAttribute.equals(objectURI))
                            {
                                markerList.add(marker);
                            }
                        }
                    }
                    catch (CoreException ce)
                    {
                        // Ignore bad files
                    }
                }
            }
        }

        return (IMarker[]) markerList.toArray(new IMarker[markerList.size()]);
    }

    /**
     * Return the IFile associated with the given resource
     * 
     * @param resource the EMF resource
     * @return the containing IFile or <code>null</code> if the resource is not an IFile
     */
    private static IFile getFile(Resource resource)
    {
        URI uri = resource.getURI();
        uri = resource.getResourceSet().getURIConverter().normalize(uri);
        String scheme = uri.scheme();
        if ("platform".equals(scheme) && uri.segmentCount() > 1 && "resource".equals(uri.segment(0)))
        {
            StringBuffer platformResourcePath = new StringBuffer();
            for (int j = 1; j < uri.segmentCount(); ++j)
            {
                platformResourcePath.append('/');
                platformResourcePath.append(uri.segment(j));
            }
            return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourcePath.toString()));
        }
        return null;
    }

}