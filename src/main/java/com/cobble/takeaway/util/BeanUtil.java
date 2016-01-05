package com.cobble.takeaway.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class BeanUtil {
	private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);
	
	private static WebApplicationContext webAppContext = null;
	private static ClassPathXmlApplicationContext classPathXmlAppContext = null;

	public static Object get(String beanName) {
		if (webAppContext != null) {
			return webAppContext.getBean(beanName);
		} else if (classPathXmlAppContext != null) {
			return classPathXmlAppContext.getBean(beanName);
		} else {
			initClassPathXmlAppContext();
		}
		return classPathXmlAppContext.getBean(beanName);
	}

	private static void initClassPathXmlAppContext() {
		classPathXmlAppContext = new ClassPathXmlApplicationContext(
				"classpath*:/applicationContext*.xml");
	}

	public static void initWebAppContext(
			WebApplicationContext webApplicationContext) {
		BeanUtil.webAppContext = webApplicationContext;
	}

	public static DefaultListableBeanFactory getBeanFactory() {
		if (webAppContext != null) {
			return (DefaultListableBeanFactory) (((XmlWebApplicationContext) webAppContext)
					.getBeanFactory());
		} else if (classPathXmlAppContext != null) {
			return (DefaultListableBeanFactory) classPathXmlAppContext
					.getBeanFactory();
		}

		return null;
	}

	public static boolean hasAnnotation(Class<?> targetClass,
			Class<? extends Annotation> annotationType) {
		return targetClass.isAnnotationPresent(annotationType);
	}

	public static boolean hasAnnotation(Method method,
			Class<? extends Annotation> annotationType) {
		return method.isAnnotationPresent(annotationType);
	}

	public static <A extends Annotation> A getAnnotation(Class<?> targetClass,
			Class<A> annotationType) {
		boolean hasAnnotation = BeanUtil.hasAnnotation(targetClass,
				annotationType);
		if (hasAnnotation) {
			return targetClass.getAnnotation(annotationType);
		} else {
			return null;
		}
	}

	public static <A extends Annotation> A getAnnotation(Method method,
			Class<A> annotationType) {
		boolean hasAnnotation = BeanUtil.hasAnnotation(method, annotationType);
		if (hasAnnotation) {
			return method.getAnnotation(annotationType);
		} else {
			return null;
		}
	}

	/**
	 * <p>
	 * find method by number of parameter
	 * </p>
	 * 
	 * @param
	 * @author Zhanzhan
	 * @Jan 15, 2014 8:45:28 AM
	 */
	public static Method getMethod(Object owner, String methodName,
			Object[] args) {
		Method[] methods = owner.getClass().getDeclaredMethods();
		Type[] paramTypeList = null;
		Method method = null;

		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				paramTypeList = m.getGenericParameterTypes();
				if (paramTypeList.length == args.length) {
					method = m;
					break;
				}
			}
		}

		return method;
	}

	public static Object invokeMethod(Object owner, String methodName)
			throws Throwable {
		return invokeMethod(owner, methodName, new Object[0]);
	}

	public static Object invokeMethod(Object owner, String methodName,
			Object[] args) throws Exception {
		Method method = getMethod(owner, methodName, args);
		if (null == method) {
			throw new Exception("can't find method:" + methodName + " in "
					+ owner.getClass());
		}
		return method.invoke(owner, args);
	}

	public static Object invokeMethod(Object owner, String methodName,
			Object[] parameterValues, Class<?>[] parameterTypes)
			throws Exception {
		Class<?> ownerClass = owner.getClass();
			Method method = ownerClass.getMethod(methodName, parameterTypes);
			return method.invoke(owner, parameterValues);
	}

	public static Object invokeStaticMethod(String className,
			String methodName, Object[] args) throws Throwable {
		return invokeStaticMethod(Class.forName(className), methodName, args);
	}

	public static Object invokeStaticMethod(Class<?> ownerClass,
			String methodName, Object[] args) throws Throwable {
		Class<?>[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}
		try {
			Method method = ownerClass.getMethod(methodName, argsClass);
			return method.invoke(ownerClass, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

	public static Object getProperty(Object owner, String propertyName,
			boolean f) throws Exception {
		Class<?> ownerClass = owner.getClass();
		try {
			Field field = ownerClass.getField(propertyName);
			if (!f)
				return field;
			Object property = field.get(owner);
			return property;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static Field getProperty(Class<?> ownerClass, String propertyName)
			throws Exception {
		Field field = null;
		try {
			field = ownerClass.getDeclaredField(propertyName);
		} catch (Exception e) {
			field = getProperty(ownerClass.getSuperclass(), propertyName);
		}
		return field;
	}

	public static void setProperty(Object owner, String propertyName,
			Object value) throws Exception {
		Class<?> ownerClass = owner.getClass();
		try {
			Field field = getProperty(ownerClass, propertyName);
			field.setAccessible(true);// access private field
			field.set(owner, value);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static Object getPropertyValue(Object owner, String propertyName)
			throws Exception {
		return getProperty(owner, propertyName, true);
	}

	public static boolean beingProperty(Object owner, String propertyName)
			throws Exception {
		Object property = getProperty(owner, propertyName, false);
		return property != null;
	}

	public static void copy(Object oldPo, Object newPo) {
		copy(oldPo, newPo, false);
	}

	public static void copy(Object oldPo, Object newPo, boolean error) {
		Class<?> oldPoClass = oldPo.getClass();
		Class<?> newPoClass = newPo.getClass();
		for (Method method : oldPoClass.getMethods()) {
			if (method.getName().equals("getClass")) {
				continue;
			}
			if (isGetter(method)) {
				try {
					Object result = method.invoke(oldPo);
					if (result == null) {
						continue;
					} else if (result instanceof Collection<?>) {
						if (((Collection<?>) result).isEmpty()) {
							continue;
						}
					} else if (result.getClass().isArray()) {
						if (result instanceof Object[]) {
							if (((Object[]) result).length == 0) {
								continue;
							}
						} else if (result instanceof byte[]) {
							if (((byte[]) result).length == 0) {
								continue;
							}
						} else if (result instanceof short[]) {
							if (((short[]) result).length == 0) {
								continue;
							}
						} else if (result instanceof int[]) {
							if (((int[]) result).length == 0) {
								continue;
							}
						} else if (result instanceof long[]) {
							if (((long[]) result).length == 0) {
								continue;
							}
						} else if (result instanceof float[]) {
							if (((float[]) result).length == 0) {
								continue;
							}
						} else if (result instanceof double[]) {
							if (((double[]) result).length == 0) {
								continue;
							}
						} else if (result instanceof char[]) {
							if (((char[]) result).length == 0) {
								continue;
							}
						} else if (result instanceof boolean[]) {
							if (((boolean[]) result).length == 0) {
								continue;
							}
						}
					}

					String methodName = getter2Setter(method.getName());
					Class<?> methodParam = method.getReturnType();

					try {
						Method setter = newPoClass.getMethod(methodName,
								methodParam);
						if (error) {
							logger.error("methodName = " + methodName);
							logger.error("methodParam = " + methodParam);
							logger.error("setter = " + setter);
						}
						setter.invoke(newPo, result);
					} catch (NoSuchMethodException e) {

					}
				} catch (Exception ex) {
					logger.error("{}", ex );
				}
			}
		}
	}

	public static boolean isGetter(Method method) {
		String name = method.getName();
		if (method.getParameterTypes().length == 0 && !name.equals("getClass")
				&& name.startsWith("get") && name.length() > 3) {
			return true;
		} else {
			return false;
		}
	}

	public static String setter2Getter(String methodName) {
		if (methodName.startsWith("set")) {
			return methodName.replaceFirst("s", "g");
		} else {
			return methodName;
		}
	}

	public static String getter2Setter(String methodName) {
		if (methodName.startsWith("get")) {
			return methodName.replaceFirst("g", "s");
		} else {
			return methodName;
		}
	}

	public static String property2Getter(String property) {
		String firstStr = property.substring(0, 1).toUpperCase();
		String methodName = "get" + firstStr + property.substring(1);
		return methodName;
	}

	public static void main(String[] args) {
	}
}
