/*
 * Copyright (c) 2003-2006, Simon Brown
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 *
 *   - Neither the name of Pebble nor the names of its contributors may
 *     be used to endorse or promote products derived from this software
 *     without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.sourceforge.pebble.event.blogentry;

import net.sourceforge.pebble.domain.BlogEntry;
import net.sourceforge.pebble.domain.SingleBlogTestCase;
import net.sourceforge.pebble.util.SecurityUtils;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests for the MarkPendingListener class.
 *
 * @author Simon Brown
 */
public class MarkPendingListenerTest extends SingleBlogTestCase {

  private MarkPendingListener listener;
  private BlogEntry blogEntry;
  private BlogEntryEvent blogEntryEvent;

  /**
   * Common setup code.
   */
  public void setUp() {
    super.setUp();

    listener = new MarkPendingListener();
    blogEntry = blog.getBlogForToday().createBlogEntry();
    SecurityUtils.runAsBlogContributor();
  }

  /**
   * Tests the blogEntryAdded() method.
   */
  public void testBlogEntryAdded() {
    SecurityUtils.runAsBlogOwner();

    assertTrue(blogEntry.isApproved());
    blogEntryEvent = new BlogEntryEvent(blogEntry, BlogEntryEvent.BLOG_ENTRY_ADDED);
    listener.blogEntryAdded(blogEntryEvent);
    assertTrue(blogEntry.isApproved());

    SecurityUtils.runAsBlogContributor();
    blogEntryEvent = new BlogEntryEvent(blogEntry, BlogEntryEvent.BLOG_ENTRY_ADDED);
    listener.blogEntryAdded(blogEntryEvent);
    assertTrue(blogEntry.isPending());
  }

  /**
   * Tests the blogEntryChanged() method, when a blog owner changes
   * the blog entry.
   */
  public void testBlogEntryChangedByBlogOwner() {
    SecurityUtils.runAsBlogOwner();

    assertTrue(blogEntry.isApproved());
    List propertyChangeEvents = new ArrayList();
    PropertyChangeEvent pce = new PropertyChangeEvent(blogEntry, BlogEntry.TITLE_PROPERTY, null, null);
    propertyChangeEvents.add(pce);
    blogEntryEvent = new BlogEntryEvent(blogEntry, propertyChangeEvents);
    listener.blogEntryChanged(blogEntryEvent);
    assertTrue(blogEntry.isApproved());
  }

  /**
   * Tests the blogEntryChanged() method, when a blog contributor changes
   * the blog entry.
   */
  public void testBlogEntryChangedByBlogContributor() {
    assertTrue(blogEntry.isApproved());
    List propertyChangeEvents = new ArrayList();
    PropertyChangeEvent pce = new PropertyChangeEvent(blogEntry, BlogEntry.TITLE_PROPERTY, null, null);
    propertyChangeEvents.add(pce);
    blogEntryEvent = new BlogEntryEvent(blogEntry, propertyChangeEvents);
    listener.blogEntryChanged(blogEntryEvent);
    assertTrue(blogEntry.isPending());
  }

  /**
   * Tests the blogEntryChanged() method when the title is changed.
   */
  public void testBlogEntryChangedForTitleProperty() {
    assertTrue(blogEntry.isApproved());
    List propertyChangeEvents = new ArrayList();
    PropertyChangeEvent pce = new PropertyChangeEvent(blogEntry, BlogEntry.TITLE_PROPERTY, null, null);
    propertyChangeEvents.add(pce);
    blogEntryEvent = new BlogEntryEvent(blogEntry, propertyChangeEvents);
    listener.blogEntryChanged(blogEntryEvent);
    assertTrue(blogEntry.isPending());
  }

  /**
   * Tests the blogEntryChanged() method when the excerpt is changed.
   */
  public void testBlogEntryChangedForExcerptProperty() {
    assertTrue(blogEntry.isApproved());
    List propertyChangeEvents = new ArrayList();
    PropertyChangeEvent pce = new PropertyChangeEvent(blogEntry, BlogEntry.EXCERPT_PROPERTY, null, null);
    propertyChangeEvents.add(pce);
    blogEntryEvent = new BlogEntryEvent(blogEntry, propertyChangeEvents);
    listener.blogEntryChanged(blogEntryEvent);
    assertTrue(blogEntry.isPending());
  }

  /**
   * Tests the blogEntryChanged() method when the body is changed.
   */
  public void testBlogEntryChangedForBodyProperty() {
    assertTrue(blogEntry.isApproved());
    List propertyChangeEvents = new ArrayList();
    PropertyChangeEvent pce = new PropertyChangeEvent(blogEntry, BlogEntry.BODY_PROPERTY, null, null);
    propertyChangeEvents.add(pce);
    blogEntryEvent = new BlogEntryEvent(blogEntry, propertyChangeEvents);
    listener.blogEntryChanged(blogEntryEvent);
    assertTrue(blogEntry.isPending());
  }

  /**
   * Tests the blogEntryChanged() method when the categories are changed.
   */
  public void testBlogEntryChangedForCategoriesProperty() {
    assertTrue(blogEntry.isApproved());
    List propertyChangeEvents = new ArrayList();
    PropertyChangeEvent pce = new PropertyChangeEvent(blogEntry, BlogEntry.CATEGORIES_PROPERTY, null, null);
    propertyChangeEvents.add(pce);
    blogEntryEvent = new BlogEntryEvent(blogEntry, propertyChangeEvents);
    listener.blogEntryChanged(blogEntryEvent);
    assertTrue(blogEntry.isPending());
  }

  /**
   * Tests the blogEntryChanged() method when the original permalink is changed.
   */
  public void testBlogEntryChangedForOriginalPermalinkProperty() {
    assertTrue(blogEntry.isApproved());
    List propertyChangeEvents = new ArrayList();
    PropertyChangeEvent pce = new PropertyChangeEvent(blogEntry, BlogEntry.ORIGINAL_PERMALINK_PROPERTY, null, null);
    propertyChangeEvents.add(pce);
    blogEntryEvent = new BlogEntryEvent(blogEntry, propertyChangeEvents);
    listener.blogEntryChanged(blogEntryEvent);
    assertTrue(blogEntry.isPending());
  }

  /**
   * Tests the blogEntryChanged() method when the attachment is changed.
   */
  public void testBlogEntryChangedForAttachmentProperty() {
    assertTrue(blogEntry.isApproved());
    List propertyChangeEvents = new ArrayList();
    PropertyChangeEvent pce = new PropertyChangeEvent(blogEntry, BlogEntry.ATTACHMENT_PROPERTY, null, null);
    propertyChangeEvents.add(pce);
    blogEntryEvent = new BlogEntryEvent(blogEntry, propertyChangeEvents);
    listener.blogEntryChanged(blogEntryEvent);
    assertTrue(blogEntry.isPending());
  }

  /**
   * Tests the blogEntryChanged() method when the comments enabled is changed.
   */
  public void testBlogEntryChangedForCommentsEnabledProperty() {
    assertTrue(blogEntry.isApproved());
    List propertyChangeEvents = new ArrayList();
    PropertyChangeEvent pce = new PropertyChangeEvent(blogEntry, BlogEntry.COMMENTS_ENABLED_PROPERTY, null, null);
    propertyChangeEvents.add(pce);
    blogEntryEvent = new BlogEntryEvent(blogEntry, propertyChangeEvents);
    listener.blogEntryChanged(blogEntryEvent);
    assertTrue(blogEntry.isApproved());
  }

}